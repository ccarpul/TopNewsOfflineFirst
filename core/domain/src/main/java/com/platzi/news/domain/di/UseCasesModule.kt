package com.platzi.news.domain.di

import com.platzi.news.data.repository.NewsRepository
import com.platzi.news.domain.usecases.GetLocalSavedNewsUseCase
import com.platzi.news.domain.usecases.IGetLocalSavedNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun provideGetLocalNewsUseCase(
         newsRepository: NewsRepository
    ): IGetLocalSavedNewsUseCase = GetLocalSavedNewsUseCase(newsRepository)
}