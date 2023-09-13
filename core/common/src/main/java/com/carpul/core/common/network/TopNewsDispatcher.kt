package com.carpul.core.common.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val topNewsDispatchers: TopNewsDispatchers)

enum class TopNewsDispatchers {
    IO,
}