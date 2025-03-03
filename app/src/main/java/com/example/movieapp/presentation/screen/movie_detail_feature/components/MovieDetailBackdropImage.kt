package com.example.movieapp.presentation.screen.movie_detail_feature.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R
import com.example.movieapp.presentation.components.commo.AsyncImageUrl

@Composable
fun MovieDetailBackdropImage(
    backdropImageUrl: String,
    modifier: Modifier
) {
    Box(modifier = modifier) {
        AsyncImageUrl(
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            imageUrl = backdropImageUrl,
        )
    }
}

@Preview
@Composable
private fun MovieDetailBackdropImagePreview() {
    MovieDetailBackdropImage(
        backdropImageUrl = "",
        modifier = Modifier.fillMaxSize()
            .height(200.dp)
    )
}