package com.platzi.core.database.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = MainNewsEntity.TABLE_NAME)
data class MainNewsEntity(
    val id: String,
    val author: String,
    val content: String,
    val description: String,
    @ColumnInfo(name = "publish_at")
    val publishedAt: String,
    val sourceId: String,
    val sourceName: String,
    @PrimaryKey
    val title: String,
    val url: String,
    val urlToImage: String,
) {
    companion object {
        const val TABLE_NAME = "main_news"
    }
}