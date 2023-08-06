package com.platzi.news.domain.usecases

import com.platzi.news.data.repository.NewsRepository
import javax.inject.Inject

class GetLocalMainNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    operator fun invoke() = newsRepository.getMainNewsDao()
}