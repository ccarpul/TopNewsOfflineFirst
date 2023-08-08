package com.platzi.news.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.platzi.news.navigation.PlatziNavHost

@Composable
fun MainContent(navController: NavHostController) {
    Background {
        PlatziNavHost(navController)
    }
}