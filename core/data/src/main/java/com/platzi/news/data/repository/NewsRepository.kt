package com.platzi.news.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.platzi.core.database.room.dao.MainNewsDao
import com.platzi.core.database.room.model.asExternalModelArticle
import com.platzi.core.model.Article
import com.platzi.core.network.api.INewsNetworkDataSource
import com.platzi.news.data.repository.paging.MainNewsRemoteMediator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val mainNewsDao: MainNewsDao,
    private val mainNewsRemoteMediator: MainNewsRemoteMediator,
) : INewsRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getMainNewsPagedDao(): Flow<PagingData<Article>> =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                prefetchDistance = 1,
                initialLoadSize = 5,
            ),
            pagingSourceFactory = {
                mainNewsDao.getMainNewsPaged()
            },
            remoteMediator = mainNewsRemoteMediator
        ).flow.map { pagingData -> pagingData.map { it.asExternalModelArticle() }}

}