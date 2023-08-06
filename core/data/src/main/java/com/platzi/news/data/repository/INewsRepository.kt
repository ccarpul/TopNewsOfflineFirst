package com.platzi.news.data.repository

import com.platzi.core.model.Article
import com.platzi.news.data.Syncable
import kotlinx.coroutines.flow.Flow

interface INewsRepository : Syncable {
    fun getMainNewsDao(): Flow<List<Article>>
    suspend fun getMainNewsApi(
        page: Int,
        pageSize: Int,
        country: String,
    ): Boolean
}