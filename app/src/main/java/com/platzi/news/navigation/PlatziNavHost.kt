package com.platzi.news.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.platzi.feature.mainnews.navigation.mainNewsRoute
import com.platzi.feature.mainnews.navigation.mainNewsScreen
import com.platzi.feature.mainnews.navigation.savedNewsScreen

@Composable
fun PlatziNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = mainNewsRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        mainNewsScreen ()
        savedNewsScreen()
    }
}