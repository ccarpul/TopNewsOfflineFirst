package com.carpul.feature.mainnews.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.carpul.feature.mainnews.MainNewsRoute

const val mainNewsRoute = "main_news_route"

fun NavController.navigateToMainNews(navOptions: NavOptions? = null) =
    navigate(mainNewsRoute, navOptions)


fun NavGraphBuilder.mainNewsScreen() {
    composable(route = mainNewsRoute) {
        MainNewsRoute()
    }
}