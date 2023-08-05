package com.platzi.news.data.repository

import com.platzi.news.data.dto.NewsResource
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    suspend fun getEverythingNews(keyword: String): Flow<List<NewsResource>>
}