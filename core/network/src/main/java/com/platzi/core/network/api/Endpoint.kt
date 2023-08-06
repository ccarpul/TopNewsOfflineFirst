package com.platzi.core.network.api

import com.platzi.core.network.BuildConfig

object Endpoint {

    const val MAIN_NEWS = "/${BuildConfig.API_VERSION}/top-headlines?${BuildConfig.NEWS_API_KEY}"
}