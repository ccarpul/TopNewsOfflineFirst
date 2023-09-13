package com.carpul.core.network.api

import com.carpul.core.network.BuildConfig
import com.carpul.core.network.di.OkHttpClientQualifier
import com.carpul.core.network.model.NetworkArticles
import okhttp3.OkHttpClient
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
class RetrofitMainNewsNetwork @Inject constructor(
    @OkHttpClientQualifier.SafeHttpClient okHttpClient: OkHttpClient
): RetrofitBaseMainNews(okHttpClient)

@Singleton
class RetrofitUnSafeMainNewsNetwork @Inject constructor(
    @OkHttpClientQualifier.UnSafeHttpClient okHttpClient: OkHttpClient
): RetrofitBaseMainNews(okHttpClient)

open class RetrofitBaseMainNews(
    okHttpClient: OkHttpClient
): INewsNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(BuildConfig.API_PROTOCOL_SECURE + BuildConfig.API_DOMAIN_DEV)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build().run {
            create(RetrofitMainNewsNetworkApi::class.java)
        }

    override suspend fun fetchMainNewsNetworkResources(
        page: Int,
        pageSize: Int,
        country: String,
    ): NetworkArticles =
        networkApi.fetchArticles(
            page,
            pageSize,
            country
        )
}