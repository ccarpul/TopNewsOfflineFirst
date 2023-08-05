package com.platzi.news.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.platzi.news.navigation.PlatziNavHost

@Composable
fun PlatziApp(navController: NavHostController = rememberNavController()) {
    PlatziNavHost(navController)
}