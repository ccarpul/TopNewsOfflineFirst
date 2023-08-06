package com.platzi.news

import android.app.Application
import com.platzi.news.data.Sync
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Sync.initialize(context = this)
    }
}