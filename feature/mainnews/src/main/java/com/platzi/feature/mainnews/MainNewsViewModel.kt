package com.platzi.feature.mainnews

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platzi.news.domain.usecases.GetEverythingNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainNewsViewModel @Inject constructor(
    private val getEverythingNewsUseCase: GetEverythingNewsUseCase
): ViewModel() {

    fun getMainNews(keyword: String) {
        viewModelScope.launch {
            getEverythingNewsUseCase(keyword).collect {
                Log.i("Collect", it.toString())
            }
        }
    }
}