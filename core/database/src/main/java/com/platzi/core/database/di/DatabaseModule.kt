package com.platzi.core.database.di

import android.content.Context
import androidx.room.Room
import com.platzi.core.database.room.database.MainNewsRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesMainNewsDatabase(
        @ApplicationContext context: Context,
    ): MainNewsRoomDatabase = Room.databaseBuilder(
        context,
        MainNewsRoomDatabase::class.java,
        "main-news-database",
    ).build()
}