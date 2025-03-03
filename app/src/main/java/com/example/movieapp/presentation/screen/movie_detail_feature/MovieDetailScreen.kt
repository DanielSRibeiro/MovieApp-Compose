package com.example.movieapp.presentation.screen.movie_detail_feature

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.core.domain.model.Movie
import com.example.movieapp.R
import com.example.movieapp.presentation.components.commo.MovieAppBar
import com.example.movieapp.presentation.screen.movie_detail_feature.components.MovieDetailContent
import com.example.movieapp.presentation.screen.movie_detail_feature.state.MovieDetailState

@Composable
fun MovieDetailScreen(
    uiState: MovieDetailState,
    onAddFavorite: (Movie) -> Unit,
) {
    val pagingMovies = uiState.results.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MovieAppBar(title = R.string.detail_movie)
        },
    ) { padding ->
        MovieDetailContent(
            modifier = Modifier.padding(padding),
            movieDetail = uiState.movieDetail,
            pagingMovieSimilar = pagingMovies,
            isLoading = uiState.isLoading,
            isError = uiState.error.toString(),
            iconColor = uiState.iconColor,
            onAddFavorite = { movie ->
                onAddFavorite(movie)
            }
        )
    }
}