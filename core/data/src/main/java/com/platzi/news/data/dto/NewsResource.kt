package com.platzi.news.data.dto

import com.platzi.core.network.model.NetworkNewsResource

data class NewsResource(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val sourceModel: SourceModel,
    val title: String,
    val url: String,
    val urlToImage: String,
)

data class SourceModel(
    val id: String?,
    val name: String?
)

fun NetworkNewsResource.asArticleResource() = NewsResource(
    author = author.orEmpty(),
    content = content.orEmpty(),
    description = description.orEmpty(),
    publishedAt = publishedAt.orEmpty(),
    sourceModel = SourceModel(
        id = sourceModel?.id.orEmpty(),
        name = sourceModel?.name.orEmpty()
    ),
    title = title.orEmpty(),
    url = url.orEmpty(),
    urlToImage = urlToImage.orEmpty()
)