package com.platzi.feature.mainnews.navigation

import com.platzi.core.common.TestConstants
import com.platzi.core.model.Article
import com.platzi.news.domain.usecases.IGetLocalSavedNewsUseCase
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