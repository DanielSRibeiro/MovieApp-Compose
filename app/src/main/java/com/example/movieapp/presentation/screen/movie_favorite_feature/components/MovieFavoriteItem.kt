package com.example.movieapp.presentation.screen.movie_favorite_feature.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.core.domain.model.Movie
import com.example.movieapp.R
import com.example.movieapp.presentation.components.commo.AsyncImageUrl
import com.example.movieapp.ui.theme.black
import com.example.movieapp.ui.theme.white

@Composable
fun MovieFavoriteItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClick: (id: Int) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(movie.id) }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(black),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                AsyncImageUrl(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    contentScale = ContentScale.FillWidth,
                    imageUrl = movie.imageUrl,
                )
            }
            Text(
                text = movie.title,
                maxLines = 1,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = white,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun MovieFavoriteItemPreview() {
    MovieFavoriteItem(
        movie = Movie(
            id = 1,
            title = "Homem Aranha",
            voteAverage = 7.89,
            imageUrl = ""
        ),
        onClick = {

        }
    )
}