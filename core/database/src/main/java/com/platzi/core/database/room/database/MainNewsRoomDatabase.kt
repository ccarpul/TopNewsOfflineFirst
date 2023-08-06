package com.platzi.core.database.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.platzi.core.database.room.dao.MainNewsDao
import com.platzi.core.database.room.entities.MainNewsEntity


@Database(
    entities = [MainNewsEntity::class],
    version = 1
)
abstract class MainNewsRoomDatabase : RoomDatabase() {

    abstract fun mainNewsDao(): MainNewsDao

    companion object {

        private const val MAIN_NEWS_DB = "mainNewsDb"

        @Volatile
        private var INSTANCE: MainNewsRoomDatabase? = null

        fun getDatabase(context: Context): MainNewsRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance

            synchronized(this) {
                return Room.databaseBuilder(
                    context.applicationContext,
                    MainNewsRoomDatabase::class.java,
                    MAIN_NEWS_DB
                )
                    .build()
            }
        }
    }
}