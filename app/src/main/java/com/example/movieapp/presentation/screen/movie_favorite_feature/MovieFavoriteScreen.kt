package com.example.movieapp.presentation.screen.movie_favorite_feature

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.movieapp.R
import com.example.movieapp.ui.theme.black

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieFavoriteScreen(
    uiState: MovieFavoriteState,
    navigateToDetail: (Int) -> Unit,
) {
    val movies = uiState.movies

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.favorite_movies)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black
                )
            )
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