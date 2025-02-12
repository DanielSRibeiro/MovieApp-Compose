package com.example.movieapp.presentation.movie_popular_feature.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movieapp.R
import com.example.movieapp.presentation.movie_popular_feature.presentation.components.MovieContent
import com.example.movieapp.presentation.movie_popular_feature.presentation.state.MoviePopularState
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