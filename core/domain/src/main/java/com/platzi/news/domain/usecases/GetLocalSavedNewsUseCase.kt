package com.platzi.news.domain.usecases

import com.platzi.news.data.repository.NewsRepository
import javax.inject.Inject

class GetLocalSavedNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    operator fun invoke() = newsRepository.getSavedNews()
}