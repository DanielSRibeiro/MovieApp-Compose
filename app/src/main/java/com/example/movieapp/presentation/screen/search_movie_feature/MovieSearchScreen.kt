package com.example.movieapp.presentation.screen.search_movie_feature

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieapp.R
import com.example.movieapp.presentation.components.commo.MovieAppBar
import com.example.movieapp.presentation.screen.search_movie_feature.state.MovieSearchState

@Composable
fun MovieSearchScreen(
    uiState: MovieSearchState,
    onEvent: (MovieSearchEvent) -> Unit,
    onFetch: (String) -> Unit,
    navigateToDetailMovie: (Int) -> Unit
) {
    val pagingMovies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MovieAppBar(title = R.string.search_movies)
        },
    ) { paddingValues ->
        SearchContent(
            paddingValues = paddingValues,
            pagingMovies = pagingMovies,
            query = uiState.query,
            onSearch = { onFetch(it) },
            onEvent = { onEvent(it) },
            onDetail = { movieId -> navigateToDetailMovie(movieId) }
        )
    }
}