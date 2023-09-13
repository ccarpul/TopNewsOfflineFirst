package com.carpul.news.data.repository

import androidx.paging.PagingData
import com.carpul.core.model.Article
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    fun getMainNewsPagedDao(): Flow<PagingData<Article>>

    fun updateArticle(article: Article)

    fun getSavedNews(): Flow<List<Article>>
}