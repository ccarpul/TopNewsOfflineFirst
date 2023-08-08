package com.platzi.news.domain.usecases

import com.platzi.core.model.Article
import com.platzi.news.data.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalSavedNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
    ): IGetLocalSavedNewsUseCase {
    override operator fun invoke() = newsRepository.getSavedNews()
}

interface IGetLocalSavedNewsUseCase {
    operator fun invoke(): Flow<List<Article>>
}