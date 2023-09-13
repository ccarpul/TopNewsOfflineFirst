package com.carpul.feature.savednews.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.carpul.feature.savednews.SavedNewsRoute

const val savedNewsRoute = "saved_news_route"

fun NavController.navigateToSavedNews(navOptions: NavOptions? = null) =
    navigate(savedNewsRoute, navOptions)


fun NavGraphBuilder.savedNewsScreen() {
    composable(route = savedNewsRoute) {
        SavedNewsRoute()
    }
}