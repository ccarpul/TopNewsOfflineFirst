package com.platzi.news.data.repository

import com.platzi.core.database.room.dao.MainNewsDao
import com.platzi.core.database.room.model.PopulatedMainNews
import com.platzi.core.database.room.model.asExternalModelArticle
import com.platzi.core.model.Article
import com.platzi.core.network.api.INewsNetworkDataSource
import com.platzi.core.network.model.NetworkNewsResource
import com.platzi.news.data.changeListSync
import com.platzi.news.data.model.asEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val network: INewsNetworkDataSource,
    private val mainNewsDao: MainNewsDao,
) : INewsRepository {
    override fun getMainNewsDao(): Flow<List<Article>> =
        mainNewsDao.getMainNews()
            .map { it.map(PopulatedMainNews::asExternalModelArticle) }

    override suspend fun getMainNewsApi(
        page: Int,
        pageSize: Int,
        country: String,
    ) = changeListSync(
        changeListFetcher = {
            /*network.fetchArticles(
                page,
                pageSize,
                country,
            )*/
            emptyList()
        },
        modelUpdater = {
            mainNewsDao.upsertNewsResources(it.map(NetworkNewsResource::asEntity))
        },
    )


    override suspend fun syncWith(
        page: Int,
        pageSize: Int,
        country: String,
    ) =
        changeListSync(
            changeListFetcher = {
                /*network.fetchArticles(
                    page,
                    pageSize,
                    country,
                )*/
                                emptyList()
            },
            modelUpdater = {
                mainNewsDao.upsertNewsResources(it.map(NetworkNewsResource::asEntity))
            },
        )
}