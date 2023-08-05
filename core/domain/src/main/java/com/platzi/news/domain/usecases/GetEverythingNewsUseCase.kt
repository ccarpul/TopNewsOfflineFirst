package com.platzi.news.domain.usecases

import com.platzi.news.data.dto.Article
import com.platzi.news.data.dto.NewsResource
import com.platzi.news.data.repository.NewsRepository
import com.platzi.news.domain.model.asExternalModelArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetEverythingNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(keyword: String): Flow<List<Article>> =
        newsRepository
            .getEverythingNews(keyword)
            .map { it.map(NewsResource::asExternalModelArticle) }
            .flowOn(Dispatchers.IO)

}