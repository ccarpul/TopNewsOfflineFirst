package com.carpul.feature.savednews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carpul.core.common.network.Dispatcher
import com.carpul.core.common.network.TopNewsDispatchers
import com.carpul.core.model.Article
import com.carpul.news.domain.usecases.IGetLocalSavedNewsUseCase
import com.carpul.news.domain.usecases.UpdateArticleUseCase
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
    @Dispatcher(TopNewsDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
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