package com.carpul.news

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
import com.carpul.feature.mainnews.navigation.navigateToMainNews
import com.carpul.feature.savednews.navigation.navigateToSavedNews
import com.carpul.news.ui.components.MainContent
import com.carpul.news.ui.components.MyBottomBar
import com.carpul.news.ui.components.MyTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNewsApp(
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
fun TopNewsAppPreview() {
    TopNewsApp()
}