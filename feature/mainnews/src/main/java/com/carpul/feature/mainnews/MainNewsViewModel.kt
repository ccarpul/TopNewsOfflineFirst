package com.carpul.feature.mainnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carpul.core.common.network.Dispatcher
import com.carpul.core.common.network.TopNewsDispatchers
import com.carpul.core.model.Article
import com.carpul.news.domain.usecases.GetLocalMainNewsPagedUseCase
import com.carpul.news.domain.usecases.UpdateArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainNewsViewModel @Inject constructor(
    getLocalMainNewsPagedUseCase: GetLocalMainNewsPagedUseCase,
    private val updateArticleUseCase: UpdateArticleUseCase,
    @Dispatcher(TopNewsDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val mainNewsPagedUiState = getLocalMainNewsPagedUseCase()
    fun updateSaveArticle(article: Article) {
        viewModelScope.launch(ioDispatcher) {
            updateArticleUseCase(article)
        }
    }
}