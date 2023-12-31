package com.carpul.core.database.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.carpul.core.model.Article

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
    @ColumnInfo(name = "page")
    var page: Int,
    val isSaved: Boolean
) {
    companion object {
        const val TABLE_NAME = "main_news"
    }
}

fun Article.asEntity() = MainNewsEntity(
    id = sourceId,
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    sourceId = sourceId,
    sourceName = sourceName,
    title = title,
    url = url,
    urlToImage = urlToImage,
    page = 0,
    isSaved = false
)