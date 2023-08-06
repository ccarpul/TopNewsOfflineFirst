package com.platzi.core.network.api

import com.platzi.core.network.BuildConfig
import com.platzi.core.network.model.NetworkArticles
import com.platzi.core.network.model.NetworkNewsResource
import com.platzi.core.network.unsafeOkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitMainNewsNetworkApi {
    @GET(Endpoint.MAIN_NEWS)
    suspend fun fetchArticles(
        @Query("page") page: Int = 1,
        @Query("pagesize") pageSize: Int = 20,
        @Query("country") country: String,
    ): NetworkArticles
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

    override suspend fun fetchArticles(
        page: Int,
        pageSize: Int,
        country: String,
    ): List<NetworkNewsResource> =
        networkApi.fetchArticles(
            page,
            pageSize,
            country
        ).articleResponse
}