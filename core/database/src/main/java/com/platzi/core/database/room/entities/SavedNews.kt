package com.platzi.core.database.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.platzi.core.model.Article

@Entity(tableName = SavedNewsEntity.TABLE_NAME)
data class SavedNewsEntity(
    val id: String,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val sourceId: String,
    val sourceName: String,
    @PrimaryKey
    val title: String,
    val url: String,
    val urlToImage: String
) {
    companion object {
        const val TABLE_NAME = "saved_news"
    }
}

fun Article.asSavedEntity() = SavedNewsEntity(
    id = sourceId,
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    sourceId = sourceId,
    sourceName = sourceName,
    title = title,
    url = url,
    urlToImage = urlToImage
)


fun SavedNewsEntity.asExternalArticle() = Article(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    sourceId = sourceId,
    sourceName = sourceName,
    title = title,
    url = url,
    urlToImage = urlToImage,
    isSaved = true
)