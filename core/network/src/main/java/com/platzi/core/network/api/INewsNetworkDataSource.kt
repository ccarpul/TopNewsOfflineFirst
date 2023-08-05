package com.platzi.core.network.api

import com.platzi.core.network.model.NetworkNewsResource
import kotlinx.coroutines.flow.Flow

interface INewsNetworkDataSource {

    fun fetchArticles(keyword: String): Flow<List<NetworkNewsResource>>
}