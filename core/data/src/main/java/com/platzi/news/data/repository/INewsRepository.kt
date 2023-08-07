package com.platzi.news.data.repository

import androidx.paging.PagingData
import com.platzi.core.model.Article
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    fun getMainNewsPagedDao(): Flow<PagingData<Article>>
}