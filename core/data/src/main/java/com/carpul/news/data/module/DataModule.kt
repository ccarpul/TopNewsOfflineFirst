package com.carpul.news.data.module

import com.carpul.news.data.repository.INewsRepository
import com.carpul.news.data.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNewsResourceRepository(
        newsRepository: NewsRepository,
    ): INewsRepository
}