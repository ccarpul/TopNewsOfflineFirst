package com.platzi.feature.mainnews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.platzi.core.model.Article
import kotlin.math.absoluteValue
import androidx.compose.ui.util.lerp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun MainNewsRoute(
    viewModel: MainNewsViewModel = hiltViewModel(),
) {

    val state = viewModel.mainNewsPagedUiState.collectAsLazyPagingItems()

    MainNewsGridPaged(state) {}
}

@Composable
fun LoadingState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        LoadingAnimation()
    }
}


@Composable
fun MainNewsGridPaged(articles: LazyPagingItems<Article>, onClick: () -> Unit) {
    MainNewsHorizontalPage(articles) {
        onClick()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainNewsHorizontalPage(articles: LazyPagingItems<Article>, onClick: () -> Unit) {

    val pagerState = rememberPagerState()

    HorizontalPager(
        count = articles.itemCount,
        state = pagerState,
        itemSpacing = 16.dp,
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
        modifier = Modifier.wrapContentSize()
    ) { current ->

        articles[current]?.let { NewsCardResumed(it) }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerScope.NewsCardResumed(article: Article) {

    Card(
        modifier = Modifier
            .graphicsLayer {
                val pageOffset = calculateCurrentOffsetForPage(currentPage).absoluteValue
                lerp(
                    start = 0.85f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                }
                alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            }
            .fillMaxWidth()
            .height(300.dp),
        shape = RoundedCornerShape(12.dp),
    ) {

        NewsHeaderImage(article.urlToImage)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = article.title,
                fontSize = 18.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = article.description,
                fontSize = 14.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Row {
                Text(
                    text = article.author,
                    fontSize = 8.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                Text(
                    text = article.publishedAt,
                    fontSize = 8.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }

    }
}

@Composable
fun NewsCardExpanded() {

}

@Composable
fun NewsHeaderImage(
    headerImageUrl: String?,
) {
    AsyncImage(
        placeholder = if (LocalInspectionMode.current) {
            painterResource(coil.base.R.drawable.notification_bg)
        } else null,
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        contentScale = ContentScale.Crop,
        model = headerImageUrl,

        contentDescription = null,
    )
}

@Preview
@Composable
fun LoadingAnimationPreview() {
    LoadingAnimation()
}

@Composable
fun LoadingAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_animation))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.size(111.dp)
    )
}