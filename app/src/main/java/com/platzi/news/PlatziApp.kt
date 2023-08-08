package com.platzi.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.platzi.feature.mainnews.navigation.navigateToMainNews
import com.platzi.feature.mainnews.navigation.navigateToSavedNews
import com.platzi.news.ui.components.MainContent
import com.platzi.news.ui.components.MyBottomBar
import com.platzi.news.ui.components.MyTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlatziApp(
    navController: NavHostController = rememberNavController(),
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        topBar = { MyTopAppBar() },
        bottomBar = {
            MyBottomBar(
                destinations = TopLevelDestination.values().asList(),
                currentDestination = currentDestination,
                Modifier,
            ){
                when (it) {
                    TopLevelDestination.HOME -> navController.navigateToMainNews()
                    TopLevelDestination.SAVED -> navController.navigateToSavedNews()
                }
            }
        }
    ) {
        Column(
            Modifier.padding(it)
        ) {
            MainContent(navController)
        }
    }
}

@Preview
@Composable
fun PlatziAppPreview() {
    PlatziApp()
}