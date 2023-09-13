package com.carpul.news.domain.usecases

import com.carpul.news.data.repository.NewsRepository
import javax.inject.Inject

class GetLocalMainNewsPagedUseCase @Inject constructor(private val newsRepository: NewsRepository)  {
    operator fun invoke() = newsRepository.getMainNewsPagedDao()
}