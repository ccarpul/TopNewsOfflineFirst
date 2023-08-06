package com.platzi.core.database.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.platzi.core.database.room.entities.MainNewsEntity
import com.platzi.core.database.room.model.PopulatedMainNews
import kotlinx.coroutines.flow.Flow

@Dao
interface MainNewsDao {

    @Transaction
    @Query(
        value = """
            SELECT * FROM main_news
            ORDER BY publish_at DESC
    """,
    )
    fun getMainNews(): Flow<List<PopulatedMainNews>>

    @Upsert
    suspend fun upsertNewsResources(newsResourceEntities: List<MainNewsEntity>)
}