package com.example.movieapp.presentation.screen.movie_detail_feature

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.core.domain.model.Movie
import com.example.movieapp.R
import com.example.movieapp.presentation.screen.movie_detail_feature.components.MovieDetailContent
import com.example.movieapp.presentation.screen.movie_detail_feature.state.MovieDetailState
import com.example.movieapp.ui.theme.black
import com.example.movieapp.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    id: Int?,
    uiState: MovieDetailState,
    onAddFavorite: (Movie) -> Unit,
    checkFavorite: (MovieDetailEvent.CheckedFavorite) -> Unit,
    getMovieDetail: (MovieDetailEvent.GetMovieDetail) -> Unit
) {
    val pagingMovies = uiState.results.collectAsLazyPagingItems()

    LaunchedEffect(key1 = true) {
        if (id != null) {
            getMovieDetail(MovieDetailEvent.GetMovieDetail(id))
            checkFavorite(MovieDetailEvent.CheckedFavorite(id))
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.detail_movie), color = white)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black
                )
            )
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