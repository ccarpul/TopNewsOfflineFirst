package com.platzi.feature.mainnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platzi.core.model.Article
import com.platzi.news.domain.usecases.GetEverythingNewsUseCase
import com.platzi.news.domain.usecases.GetLocalMainNewsUseCase
import com.platzi.news.domain.usecases.GetMainNewsSyncUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainNewsViewModel @Inject constructor(
    private val getEverythingNewsUseCase: GetEverythingNewsUseCase,
    private val getMainNewsSyncUseCase: GetMainNewsSyncUseCase,
    private val getLocalMainNewsUseCase: GetLocalMainNewsUseCase,
) : ViewModel() {

    val mainNewsUiState: StateFlow<MainNewsUiState> = getLocalMainNewsUseCase()
        .map<List<Article>, MainNewsUiState>(MainNewsUiState::Success)
        .onStart { emit(MainNewsUiState.Loading) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainNewsUiState.Loading,
        )

    fun getMainNews(country: String) {
        viewModelScope.launch {
            getEverythingNewsUseCase(country)
        }
    }

    fun getMainNewsSyncable(country: String) {
        viewModelScope.launch {
            getMainNewsSyncUseCase(country = country)
        }
    }
}

sealed interface MainNewsUiState {
    object Loading : MainNewsUiState
    data class Success(val mainNews: List<Article>) : MainNewsUiState
}