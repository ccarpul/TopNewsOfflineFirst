package com.platzi.core.network.model

data class NetworkNewsResource(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val sourceModel: SourceModel?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
)

data class SourceModel(
    val id: String?,
    val name: String?
)