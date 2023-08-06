package com.platzi.core.database.di

import com.platzi.core.database.room.dao.MainNewsDao
import com.platzi.core.database.room.database.MainNewsRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesNewsResourceDao(
        database: MainNewsRoomDatabase,
    ): MainNewsDao = database.mainNewsDao()
}