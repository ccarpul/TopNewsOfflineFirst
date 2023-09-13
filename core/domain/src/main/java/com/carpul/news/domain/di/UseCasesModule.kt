package com.carpul.news.domain.di

import com.carpul.news.data.repository.NewsRepository
import com.carpul.news.domain.usecases.GetLocalSavedNewsUseCase
import com.carpul.news.domain.usecases.IGetLocalSavedNewsUseCase
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