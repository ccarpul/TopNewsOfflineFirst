package com.carpul.news.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.carpul.news.navigation.NavHost

@Composable
fun MainContent(navController: NavHostController) {
    Background {
        NavHost(navController)
    }
}