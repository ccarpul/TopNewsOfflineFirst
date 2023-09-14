package com.carpul.feature.savednews

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carpul.core.model.Article
import com.carpul.core.ui.cards.NewsCardResumed
import com.carpul.core.ui.loading.LoadingState


@Composable
fun SavedNewsRoute(
    viewModel: SavedNewsViewModel = hiltViewModel(),
) {

    val state = viewModel.mainNewsUiState.collectAsStateWithLifecycle()

    when (state.value) {
        SavedNewsState.Loading -> LoadingState()
        is SavedNewsState.Success -> SavedNewsLazyList(
            (state.value as SavedNewsState.Success)
                .savedNews
        ) { article ->
            viewModel.updateSaveArticle(article)
        }
    }
}

@Composable
fun SavedNewsLazyList(
    articles: List<Article>,
    updateSaveArticle: (Article) -> Unit,
) {

        SavedNewsList(articles) { title ->
            updateSaveArticle(title)
        }
}

@Composable
fun SavedNewsList(
    articles: List<Article>,
    updateSaveArticle: (Article) -> Unit,
) {

    LazyColumn {
        item {
            Text(
                text = stringResource(R.string.saved_news_title),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(vertical = 16.dp))
        }

        items(articles) { article ->

            NewsCardResumed(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 32.dp)
                    .height(340.dp),
                article
            ) {
                updateSaveArticle(article.copy(isSaved = !article.isSaved))
            }
            Spacer(modifier = Modifier.padding(top = 8.dp))
        }
    }
}