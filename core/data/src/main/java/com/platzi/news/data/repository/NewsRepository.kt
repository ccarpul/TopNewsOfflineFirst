package com.platzi.news.data.repository

import com.platzi.core.network.api.INewsNetworkDataSource
import com.platzi.core.network.model.NetworkNewsResource
import com.platzi.news.data.dto.NewsResource
import com.platzi.news.data.dto.asArticleResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepository @Inject constructor(private val network: INewsNetworkDataSource) : INewsRepository {

    override suspend fun getEverythingNews(keyword: String): Flow<List<NewsResource>> =
        network.fetchArticles(keyword).map { it.map(NetworkNewsResource::asArticleResource) }
}