package com.platzi.core.network.api

import com.example.core.network.unsafeOkHttpClient
import com.platzi.core.network.BuildConfig
import com.platzi.core.network.model.NetworkArticles
import com.platzi.core.network.model.NetworkNewsResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitMainNewsNetworkApi {
    @GET(Endpoint.EVERYTHING_NEWS)
    suspend fun fetchArticles(@Query("q") keyword: String): NetworkArticles
}

@Singleton
class RetrofitMainNewsNetwork @Inject constructor() : INewsNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(BuildConfig.API_PROTOCOL_SECURE + BuildConfig.API_DOMAIN_DEV)
        .addConverterFactory(GsonConverterFactory.create())
        .client(unsafeOkHttpClient())
        .build().run {
            create(RetrofitMainNewsNetworkApi::class.java)
        }

    override fun fetchArticles(keyword: String): Flow<List<NetworkNewsResource>> =
       flow {
           //val result = networkApi.fetchArticles(keyword).articleResponse
           emit(emptyList())
       }
}