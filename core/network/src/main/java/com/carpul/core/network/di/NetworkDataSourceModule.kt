package com.carpul.core.network.di

import com.carpul.core.network.api.INewsNetworkDataSource
import com.carpul.core.network.api.RetrofitUnSafeMainNewsNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {

    @Binds
    fun RetrofitUnSafeMainNewsNetwork.bind(): INewsNetworkDataSource
}