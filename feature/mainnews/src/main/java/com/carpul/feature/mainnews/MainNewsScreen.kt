package com.carpul.feature.mainnews


import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.carpul.core.model.Article
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.carpul.core.ui.cards.NewsCardResumed
import com.carpul.core.ui.loading.LoadingState
import com.carpul.core.ui.pager.CustomHorizontalPager
import kotlin.math.absoluteValue

@Composable
fun MainNewsRoute(
    viewModel: MainNewsViewModel = hiltViewModel()
) {
    val state = viewModel.mainNewsPagedUiState.collectAsLazyPagingItems()
    val saveCurrentPage: (Int) -> Unit = { page -> MainNewsViewModel.currentPage = page }

    when (state.loadState.mediator?.refresh) {
        LoadState.Loading -> LoadingState()
        else -> MainNewsGridPaged(state, saveCurrentPage, MainNewsViewModel.currentPage) { article ->
            viewModel.updateSaveArticle(article)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainNewsGridPaged(
    articles: LazyPagingItems<Article>,
    saveCurrentPage: (Int) -> Unit,
    currentPage: Int,
    updateSaveArticle: (Article) -> Unit,
) {

    var page by remember { mutableStateOf(currentPage) }

    DisposableEffect(Unit) {
        onDispose {
            saveCurrentPage(page)
        }
    }

    Column {
        Text(
            text = stringResource(R.string.main_news_title),
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(vertical = 16.dp))

        CustomHorizontalPager(
            itemCount = articles.itemCount,
            currentPage = currentPage,
        ) { pagerScope, current ->
            articles[current]?.let { article ->
                page = pagerScope.currentPage
                NewsCardResumed(
                    modifier = Modifier
                        .graphicsLayer {
                            val pageOffset = pagerScope
                                .calculateCurrentOffsetForPage(current)
                                .absoluteValue

                            lerp(
                                start = 0.89f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0.2f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                            alpha = lerp(
                                start = 0.7f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        }
                        .fillMaxWidth()
                        .height(340.dp),
                    article = article
                ){
                    updateSaveArticle(article.copy(isSaved = !article.isSaved))
                }
            }
        }
    }
}

@Preview
@Composable
fun LoadingPreview() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Transparent),
        contentAlignment = Alignment.Center,
    ) { LoadingState() }
}
