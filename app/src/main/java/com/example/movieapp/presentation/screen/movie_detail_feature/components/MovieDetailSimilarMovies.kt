package com.example.movieapp.presentation.screen.movie_detail_feature.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.core.domain.model.Movie
import com.example.movieapp.presentation.components.commo.ErrorScreen
import com.example.movieapp.presentation.components.commo.LoadingView
import com.example.movieapp.presentation.screen.movie_popular_feature.components.MovieItem

@Composable
fun MovieDetailSimilarMovies(
    pagingMoviesSimilar: LazyPagingItems<Movie>,
    modifier: Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        items(pagingMoviesSimilar.itemCount) { index ->
            val movie = pagingMoviesSimilar[index]
            movie?.let {
                MovieItem(
                    voteAverage = it.voteAverage,
                    imageUrl = it.imageUrl,
                    id = it.id,
                    onClick = { id ->

                    }
                )
            }
        }
        pagingMoviesSimilar.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }) {
                        LoadingView()
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }) {
                        LoadingView()
                    }
                }

                loadState.refresh is LoadState.Loading -> {
                    val error = pagingMoviesSimilar.loadState.refresh as LoadState.Error
                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }) {
                        ErrorScreen(
                            message = error.error.message.toString()
                        ) {
                            retry()
                        }
                    }
                }

                loadState.append is LoadState.Loading -> {
                    val error = pagingMoviesSimilar.loadState.refresh as LoadState.Error
                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }) {
                        ErrorScreen(
                            message = error.error.message.toString()
                        ) {
                            retry()
                        }
                    }
                }
            }
        }
    }
}