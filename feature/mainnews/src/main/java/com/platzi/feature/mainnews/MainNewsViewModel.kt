package com.platzi.feature.mainnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platzi.core.common.network.Dispatcher
import com.platzi.core.common.network.PlatziDispatchers
import com.platzi.core.model.Article
import com.platzi.news.domain.usecases.GetLocalMainNewsPagedUseCase
import com.platzi.news.domain.usecases.UpdateArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainNewsViewModel @Inject constructor(
    getLocalMainNewsPagedUseCase: GetLocalMainNewsPagedUseCase,
    private val updateArticleUseCase: UpdateArticleUseCase,
    @Dispatcher(PlatziDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val mainNewsPagedUiState = getLocalMainNewsPagedUseCase()
    fun updateSaveArticle(article: Article) {
        viewModelScope.launch(ioDispatcher) {
            updateArticleUseCase(article)
        }
    }
}