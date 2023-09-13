package com.carpul.core.database.di

import com.carpul.core.database.room.dao.MainNewsDao
import com.carpul.core.database.room.dao.RemoteKeysDao
import com.carpul.core.database.room.dao.SavedNewsDao
import com.carpul.core.database.room.database.MainNewsRoomDatabase
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

    @Provides
    fun providesRemoteKeysDao(
        database: MainNewsRoomDatabase,
    ): RemoteKeysDao = database.remoteKeysDao()

    @Provides
    fun providesSavedNewsDao(
        database: MainNewsRoomDatabase,
    ): SavedNewsDao = database.savedNewsDao()
}