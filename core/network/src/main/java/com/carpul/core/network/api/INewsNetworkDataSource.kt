package com.carpul.core.network.api

import com.carpul.core.network.model.NetworkArticles
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