package com.example.movieapp.presentation.screen.movie_popular_feature

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieapp.R
import com.example.movieapp.presentation.components.commo.MovieAppBar
import com.example.movieapp.presentation.screen.movie_popular_feature.components.MovieContent
import com.example.movieapp.presentation.screen.movie_popular_feature.state.MoviePopularState
import com.example.movieapp.util.UtilFunctions

@Composable
fun MoviePopularScreen(
    uiState: MoviePopularState,
    navigateToDetailMovie: (Int) -> Unit
) {
    val movies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MovieAppBar(title = R.string.popular_movies)
        },
    ) { innerPadding ->
        MovieContent(
            pagingMovies = movies,
            modifier = Modifier,
            paddingValues = innerPadding,
            onClick = { movieId ->
                UtilFunctions.logInfo("MOVIE_ID", movieId.toString())
                navigateToDetailMovie(movieId)
            }
        )
    }
}