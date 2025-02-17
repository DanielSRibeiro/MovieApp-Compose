package com.example.movieapp.presentation.screen.search_movie_feature

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieapp.R
import com.example.movieapp.presentation.screen.search_movie_feature.state.MovieSearchState
import com.example.movieapp.ui.theme.black
import com.example.movieapp.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
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
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.search_movies),
                        color = white
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black
                )
            )
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