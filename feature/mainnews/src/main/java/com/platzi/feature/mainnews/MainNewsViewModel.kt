package com.platzi.feature.mainnews

import androidx.lifecycle.ViewModel
import com.platzi.news.domain.usecases.GetLocalMainNewsPagedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainNewsViewModel @Inject constructor(
    getLocalMainNewsPagedUseCase: GetLocalMainNewsPagedUseCase,
) : ViewModel() {

    val mainNewsPagedUiState = getLocalMainNewsPagedUseCase()
}