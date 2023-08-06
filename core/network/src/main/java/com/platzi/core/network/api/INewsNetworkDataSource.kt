package com.platzi.core.network.api

import com.platzi.core.network.model.NetworkNewsResource

interface INewsNetworkDataSource {

    suspend fun fetchArticles(
        page: Int,
        pageSize: Int,
        country: String,
    ): List<NetworkNewsResource>
}