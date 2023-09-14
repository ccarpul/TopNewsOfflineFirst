package com.carpul.core.ui.pager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.rememberPagerState


const val MAX_DOTS_INDICATOR = 9
const val CENTER_DOT_INDICATOR = 4

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomHorizontalPager(
    itemCount: Int,
    currentPage: Int,
    content: @Composable (PagerScope, Int) -> Unit,
) {
    val pagerState = rememberPagerState(initialPage = currentPage)

    val indexingPageIndicator: (Int) -> Int = { page ->

        if (itemCount < MAX_DOTS_INDICATOR) page
        else {
            when {
                page <= CENTER_DOT_INDICATOR -> page
                itemCount - page > CENTER_DOT_INDICATOR -> CENTER_DOT_INDICATOR
                else -> MAX_DOTS_INDICATOR - (itemCount - page)
            }
        }
    }
    HorizontalPager(
        count = itemCount,
        state = pagerState,
        itemSpacing = 0.dp,
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
        modifier = Modifier.wrapContentSize()
    ) { current ->
        content(this, current)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        HorizontalPagerIndicator(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 32.dp),
            pagerState = pagerState,
            pageCount = if (itemCount < MAX_DOTS_INDICATOR) itemCount else MAX_DOTS_INDICATOR,
            activeColor = MaterialTheme.colorScheme.tertiary,
            pageIndexMapping = indexingPageIndicator
        )
    }
}