package com.platzi.news.data.model

import com.platzi.core.database.room.entities.MainNewsEntity
import com.platzi.core.network.model.NetworkNewsResource

fun NetworkNewsResource.asEntity() = MainNewsEntity(
    id = sourceModel?.id.orEmpty(),
    author = author.orEmpty(),
    content = content.orEmpty(),
    description = description.orEmpty(),
    publishedAt = publishedAt.orEmpty(),
    sourceId = sourceModel?.id.orEmpty(),
    sourceName = sourceModel?.name.orEmpty(),
    title = title.orEmpty(),
    url = url.orEmpty(),
    urlToImage = urlToImage.orEmpty(),
    page = 0
)