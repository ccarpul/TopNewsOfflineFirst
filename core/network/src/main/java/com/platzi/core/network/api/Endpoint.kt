package com.platzi.core.network.api

import com.platzi.core.network.BuildConfig

object Endpoint {

    const val EVERYTHING_NEWS = "/${BuildConfig.API_VERSION}/everything?${BuildConfig.NEWS_API_KEY}"
}