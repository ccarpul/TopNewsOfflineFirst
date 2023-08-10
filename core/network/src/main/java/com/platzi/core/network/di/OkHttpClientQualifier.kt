package com.platzi.core.network.di

import javax.inject.Qualifier

interface OkHttpClientQualifier {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UnSafeHttpClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class SafeHttpClient
}