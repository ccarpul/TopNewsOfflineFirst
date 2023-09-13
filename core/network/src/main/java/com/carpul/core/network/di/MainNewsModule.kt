package com.carpul.core.network.di

import com.carpul.core.network.safeOkHttpClient
import com.carpul.core.network.unSafeOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainNewsModule {

    @Provides
    @Singleton
    @OkHttpClientQualifier.UnSafeHttpClient
    fun getUnSafeHttpClient(): OkHttpClient = unSafeOkHttpClient()

    @Provides
    @Singleton
    @OkHttpClientQualifier.SafeHttpClient
    fun getSafeHttpClient() = safeOkHttpClient()
}