package com.example.movieapp.presentation.components.commo

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.R

@Composable
fun AsyncImageUrl(
    modifier: Modifier = Modifier,
    contentScale: ContentScale,
    imageUrl: String,
    crossFadeEnabled: Boolean = true,
    @DrawableRes errorImage: Int = R.drawable.ic_error_image,
    @DrawableRes placeholderImage: Int = R.drawable.ic_placeholder,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(crossFadeEnabled)
            .error(errorImage)
            .placeholder(placeholderImage)
            .build(),
        contentDescription = "",
        contentScale = contentScale,
        modifier = modifier
    )
}