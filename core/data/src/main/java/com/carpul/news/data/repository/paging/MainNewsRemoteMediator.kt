package com.carpul.news.data.repository.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.carpul.core.database.room.dao.MainNewsDao
import com.carpul.core.database.room.dao.RemoteKeysDao
import com.carpul.core.database.room.database.MainNewsRoomDatabase
import com.carpul.core.database.room.entities.RemoteKeysEntity
import com.carpul.core.database.room.model.PopulatedMainNews
import com.carpul.core.model.Config
import com.carpul.core.network.api.INewsNetworkDataSource
import com.carpul.core.network.model.NetworkNewsResource
import com.carpul.news.data.model.asEntity
import com.carpul.news.data.util.getRemoteKeyClosestToCurrentPosition
import com.carpul.news.data.util.getRemoteKeyForFirstItem
import com.carpul.news.data.util.getRemoteKeyForLastItem
import com.carpul.news.data.util.isInitialLaunch
import kotlinx.coroutines.delay
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class MainNewsRemoteMediator @Inject constructor(
    private val network: INewsNetworkDataSource,
    private val mainNewsDao: MainNewsDao,
    private val remoteKeysDao: RemoteKeysDao,
    private val mainNewsRoomDatabase: MainNewsRoomDatabase,
) : RemoteMediator<Int, PopulatedMainNews>() {

    override suspend fun initialize() =
        if (remoteKeysDao.getCreationTime().isInitialLaunch())
            InitializeAction.SKIP_INITIAL_REFRESH
        else
            InitializeAction.LAUNCH_INITIAL_REFRESH


    @ExperimentalPagingApi
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PopulatedMainNews>,
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> remoteKeysDao
                .getRemoteKeyClosestToCurrentPosition(state)
                ?.nextKey?.minus(1) ?: 1

            LoadType.PREPEND -> {
                val remoteKeys = remoteKeysDao.getRemoteKeyForFirstItem(state)
                remoteKeys
                    ?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }

            LoadType.APPEND -> {
                val remoteKeys = remoteKeysDao.getRemoteKeyForLastItem(state)
                remoteKeys
                    ?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        return try {
            val mainNews = network.fetchMainNewsNetworkResources(
                page = page,
                pageSize = INewsNetworkDataSource.PAGE_SIZE,
                country = Config.country
            )
            delay(2000L) //For testing
            val endOfPaginationReached = mainNews.articleResponse.isEmpty()

            mainNewsRoomDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeysDao.clearRemoteKeys()
                    mainNewsDao.clearAllArticles()
                }

                val remoteKeys = mainNews
                    .articleResponse.map {
                        RemoteKeysEntity(
                            articleId = it.title.orEmpty(),
                            prevKey = if (page > 1) page - 1 else null,
                            currentPage = page,
                            nextKey = if (endOfPaginationReached) null else page + 1
                        )
                    }

                remoteKeysDao.insertAll(remoteKeys)
                mainNewsDao.upsertNewsResources(
                    mainNews.articleResponse.map(NetworkNewsResource::asEntity)
                        .onEachIndexed { _, article ->
                            article.page = page
                        }
                )
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (ex: Exception) {
            when (ex) {
                is IOException -> MediatorResult.Error(ex)
                else -> MediatorResult.Error(ex)
            }
        }
    }
}
