package com.example.movieapp.presentation.screen.movie_popular_feature

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieapp.R
import com.example.movieapp.presentation.screen.movie_popular_feature.components.MovieContent
import com.example.movieapp.presentation.screen.movie_popular_feature.state.MoviePopularState
import com.example.movieapp.ui.theme.black
import com.example.movieapp.ui.theme.white
import com.example.movieapp.util.UtilFunctions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviePopularScreen(
    uiState: MoviePopularState,
    navigateToDetailMovie: (Int) -> Unit
) {
    val movies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.popular_movies),
                        color = white
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black
                )
            )
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