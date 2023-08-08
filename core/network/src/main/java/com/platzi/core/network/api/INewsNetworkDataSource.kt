package com.platzi.core.network.api

import com.platzi.core.network.model.NetworkArticles
interface INewsNetworkDataSource {

    companion object {
        const val PAGE_SIZE = 5
    }
    suspend fun fetchMainNewsNetworkResources(
        page: Int,
        pageSize: Int,
        country: String,
    ): NetworkArticles
}