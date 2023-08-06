package com.platzi.news.domain.usecases

import com.platzi.news.data.Syncable
import com.platzi.news.data.repository.INewsRepository
import javax.inject.Inject

class GetMainNewsSyncUseCase @Inject constructor(
    private val newsRepository: INewsRepository,
) {

    suspend operator fun invoke(
        page: Int = Syncable.DEFAULT_PAGE,
        pageSize: Int = Syncable.DEFAULT_PAGE_SIZE,
        country: String,
    ) = newsRepository.getMainNewsApi(
        page,
        pageSize,
        country
    )
}