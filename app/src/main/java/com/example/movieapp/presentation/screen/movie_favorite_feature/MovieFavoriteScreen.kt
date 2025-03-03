package com.example.movieapp.presentation.screen.movie_favorite_feature

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.movieapp.R
import com.example.movieapp.presentation.components.commo.MovieAppBar

@Composable
fun MovieFavoriteScreen(
    uiState: MovieFavoriteState,
    navigateToDetail: (Int) -> Unit,
) {
    val movies = uiState.movies

    Scaffold(
        topBar = {
            MovieAppBar(title = R.string.favorite_movies)
        }
    ) { paddingValues ->

        MovieFavoriteContent(
            paddingValues = paddingValues,
            movies = movies
        ) { movieId ->
            navigateToDetail(movieId)
        }
    }
}