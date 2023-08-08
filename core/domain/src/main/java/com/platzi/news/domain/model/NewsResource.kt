package com.platzi.news.domain.model

import com.platzi.core.model.Article
import com.platzi.news.data.dto.NewsResource

fun NewsResource.asExternalModelArticle() = Article(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    sourceId = id,
    sourceName = name,
    title = title,
    url = url,
    urlToImage = urlToImage,
    isSaved = isSaved
)