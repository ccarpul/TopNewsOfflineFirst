package com.platzi.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.platzi.news.ui.components.Background
import com.platzi.news.ui.theme.PlatziChallengeNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlatziChallengeNewsTheme {
                PlatziApp()
            }
        }
    }
}