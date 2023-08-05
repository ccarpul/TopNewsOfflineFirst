package com.platzi.feature.mainnews

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainNewsRoute(
    viewModel: MainNewsViewModel = hiltViewModel(),
) {
    //val feedState by viewModel.feedUiState.collectAsStateWithLifecycle()
    viewModel.getMainNews("bitcoin")
    MainNewsScreen(

    )
}

@Composable
fun MainNewsScreen(

) {
}

