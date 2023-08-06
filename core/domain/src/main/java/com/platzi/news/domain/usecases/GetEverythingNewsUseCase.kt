package com.platzi.news.domain.usecases

import com.platzi.core.model.Article
import com.platzi.news.data.Syncable
import com.platzi.news.data.dto.NewsResource
import com.platzi.news.data.repository.NewsRepository
import com.platzi.news.domain.model.asExternalModelArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetEverythingNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(country: String) =
        newsRepository.getMainNewsApi(
            page = Syncable.DEFAULT_PAGE,
            pageSize = Syncable.DEFAULT_PAGE_SIZE,
            country = country
        )


}