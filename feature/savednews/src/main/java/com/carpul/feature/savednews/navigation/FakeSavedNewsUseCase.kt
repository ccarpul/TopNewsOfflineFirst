package com.carpul.feature.savednews.navigation

import com.carpul.core.common.TestConstants
import com.carpul.core.model.Article
import com.carpul.news.domain.usecases.IGetLocalSavedNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FakeSavedNewsUseCase : IGetLocalSavedNewsUseCase {

    override fun invoke(): Flow<List<Article>> =
        flow {
            emit(listOf(TestConstants.article))
        }.flowOn(Dispatchers.Unconfined)
}