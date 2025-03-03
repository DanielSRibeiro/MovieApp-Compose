package com.example.movieapp.presentation.screen.movie_favorite_feature

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.domain.model.Movie
import com.example.movieapp.R
import com.example.movieapp.presentation.screen.movie_favorite_feature.components.MovieFavoriteItem
import com.example.movieapp.ui.theme.black
import com.example.movieapp.ui.theme.white

@Composable
fun MovieFavoriteContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    movies: List<Movie>,
    onclick: (id: Int) -> Unit
) {
    Box(
        modifier = modifier.background(black)
    ) {
        if (movies.isEmpty()) {
            Text(
                text = stringResource(R.string.favorite_movies_empty),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = white,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = paddingValues,
        ) {
            items(movies.size, key = { movies[it].id }) { index ->
                MovieFavoriteItem(movie = movies[index], onClick = onclick)
            }
        }

    }
}

@Preview
@Composable
fun MovieFavoriteContentPreview() {
    MovieFavoriteContent(
        modifier = Modifier,
        paddingValues = PaddingValues(),
        movies = listOf(
            Movie(
                id = 1,
                title = "Homem Aranha",
                voteAverage = 7.89,
                imageUrl = ""
            ),
            Movie(
                id = 2,
                title = "Homem de Ferro",
                voteAverage = 7.89,
                imageUrl = ""
            ),
        )
    ) {}
}