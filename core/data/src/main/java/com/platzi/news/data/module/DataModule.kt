package com.platzi.news.data.module

import com.platzi.news.data.repository.INewsRepository
import com.platzi.news.data.repository.NewsRepository
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