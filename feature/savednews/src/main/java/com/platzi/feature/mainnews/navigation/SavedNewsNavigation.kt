package com.platzi.feature.mainnews.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.platzi.feature.mainnews.SavedNewsRoute

const val savedNewsRoute = "saved_news_route"

fun NavController.navigateToSavedNews(navOptions: NavOptions? = null) =
    navigate(savedNewsRoute, navOptions)


fun NavGraphBuilder.savedNewsScreen() {
    composable(route = savedNewsRoute) {
        SavedNewsRoute()
    }
}