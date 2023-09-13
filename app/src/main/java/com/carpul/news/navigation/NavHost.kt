package com.carpul.news.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.carpul.feature.mainnews.navigation.mainNewsRoute
import com.carpul.feature.mainnews.navigation.mainNewsScreen
import com.carpul.feature.savednews.navigation.savedNewsScreen

@Composable
fun NavHost(
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