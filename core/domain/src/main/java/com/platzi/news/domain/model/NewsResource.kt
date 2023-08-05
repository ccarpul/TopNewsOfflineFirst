package com.platzi.news.domain.model

import com.platzi.news.data.dto.Article
import com.platzi.news.data.dto.NewsResource

fun NewsResource.asExternalModelArticle() = Article(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    sourceId = sourceModel.id.orEmpty(),
    sourceName = sourceModel.name.orEmpty(),
    title = title,
    url = url,
    urlToImage = urlToImage,
)