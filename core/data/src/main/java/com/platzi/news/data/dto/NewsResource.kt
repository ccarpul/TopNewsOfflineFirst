package com.platzi.news.data.dto

import com.platzi.core.network.model.NetworkNewsResource
import com.platzi.core.network.model.SourceModel

data class NewsResource(
    val id: String,
    val name: String,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String,
)

fun NetworkNewsResource.asArticleResource() = NewsResource(
    id = sourceModel?.id.orEmpty(),
    name = sourceModel?.name.orEmpty(),
    author = author.orEmpty(),
    content = content.orEmpty(),
    description = description.orEmpty(),
    publishedAt = publishedAt.orEmpty(),
    title = title.orEmpty(),
    url = url.orEmpty(),
    urlToImage = urlToImage.orEmpty()
)