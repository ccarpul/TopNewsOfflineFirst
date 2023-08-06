package com.platzi.core.database.room.model

import androidx.room.Embedded
import com.platzi.core.database.room.entities.MainNewsEntity
import com.platzi.core.model.Article

data class PopulatedMainNews(
    @Embedded
    val entity: MainNewsEntity
)

fun PopulatedMainNews.asExternalModelArticle() = Article(
    author = entity.author,
    content = entity.content,
    description = entity.description,
    publishedAt = entity.publishedAt,
    sourceId = entity.sourceId,
    sourceName = entity.sourceName,
    title = entity.title,
    url = entity.url,
    urlToImage = entity.urlToImage,
)