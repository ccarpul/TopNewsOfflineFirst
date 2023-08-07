package com.platzi.news.data.util

import androidx.paging.PagingState
import com.platzi.core.database.room.dao.RemoteKeysDao
import com.platzi.core.database.room.model.PopulatedMainNews
import java.util.concurrent.TimeUnit


fun Long?.isInitialLaunch() = System.currentTimeMillis() - (this ?: 0) <
        TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)

suspend fun RemoteKeysDao.getRemoteKeyForLastItem(state: PagingState<Int, PopulatedMainNews>) =
    state
        .pages
        .lastOrNull { it.data.isNotEmpty() }
        ?.data
        ?.lastOrNull()
        ?.let { article ->
            getRemoteKeyByArticleId(article.entity.title)
        }

suspend fun RemoteKeysDao.getRemoteKeyForFirstItem(state: PagingState<Int, PopulatedMainNews>) =
    state
        .pages
        .firstOrNull { it.data.isNotEmpty() }
        ?.data
        ?.firstOrNull()
        ?.let { article ->
            getRemoteKeyByArticleId(article.entity.title)
        }


suspend fun RemoteKeysDao.getRemoteKeyClosestToCurrentPosition(
    state: PagingState<Int, PopulatedMainNews>,
) =
    state
        .pages
        .lastOrNull { it.data.isNotEmpty() }
        ?.data
        ?.lastOrNull()
        ?.let { article ->
            getRemoteKeyByArticleId(article.entity.title)
        }