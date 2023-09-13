package com.carpul.core.ui.pager

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CustomHorizontalPager(
    itemCount: Int,
    content: @Composable (PagerScope, Int) -> Unit
) {

    val pagerState = rememberPagerState()

    HorizontalPager(
        count = itemCount,
        state = pagerState,
        itemSpacing = 0.dp,
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
        modifier = Modifier.wrapContentSize()
    ) {current ->
        content(this, current)
    }
}