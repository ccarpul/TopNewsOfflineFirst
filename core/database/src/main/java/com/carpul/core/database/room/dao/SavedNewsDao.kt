package com.carpul.core.database.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.carpul.core.database.room.entities.SavedNewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: SavedNewsEntity): Long

    @Delete
    fun deleteArticle(article: SavedNewsEntity)

    @Transaction
    @Query(
        value = """
            SELECT * FROM saved_news
    """,
    )
    fun getSavedNews(): Flow<List<SavedNewsEntity>>
}