package com.carpul.core.ui.images

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.carpul.core.ui.R
import com.carpul.core.ui.buttons.SaveButton

@Composable
fun NewsHeaderImage(
    headerImageUrl: String?,
    isSaved: Boolean,
    onClick: () -> Unit,
) {
    Box {
        AsyncImage(

            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .height(180.dp),
            contentScale = ContentScale.Crop,
            model = headerImageUrl,
            contentDescription = null,
            error = painterResource(R.drawable.news_placeholder),
        )

        SaveButton(
            isSaved,
            Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        ) { onClick() }
    }
}

@Preview(name = "No saved")
@Composable
fun HeaderImagePreview() {
    NewsHeaderImage(
        "",
        false
    ) {}
}

@Preview(name = "saved")
@Composable
fun HeaderImageSavedPreview() {
    NewsHeaderImage(
        "",
        true
    ) {}
}