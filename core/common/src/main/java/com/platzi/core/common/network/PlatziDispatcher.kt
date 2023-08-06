package com.platzi.core.common.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val platziDispatchers: PlatziDispatchers)

enum class PlatziDispatchers {
    IO,
}