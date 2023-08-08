package com.platzi.feature.mainnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platzi.core.common.network.Dispatcher
import com.platzi.core.common.network.PlatziDispatchers
import com.platzi.core.model.Article
import com.platzi.news.domain.usecases.IGetLocalSavedNewsUseCase
import com.platzi.news.domain.usecases.UpdateArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    getLocalSavedNewsUseCase: IGetLocalSavedNewsUseCase,
    private val updateArticleUseCase: UpdateArticleUseCase,
    @Dispatcher(PlatziDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val mainNewsUiState: StateFlow<SavedNewsState> = getLocalSavedNewsUseCase()
        .map<List<Article>, SavedNewsState>(SavedNewsState::Success)
        .onStart { emit(SavedNewsState.Loading) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SavedNewsState.Loading,
        )
    fun updateSaveArticle(article: Article) {
        viewModelScope.launch(ioDispatcher) {
            updateArticleUseCase(article)
        }
    }
}

sealed interface SavedNewsState {
    object Loading: SavedNewsState
    data class Success(val savedNews: List<Article>): SavedNewsState
}