package com.example.movieapp.presentation.screen.search_movie_feature

import SearchComponent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.core.domain.model.MovieSearch
import com.example.movieapp.presentation.components.commo.ErrorScreen
import com.example.movieapp.presentation.components.commo.LoadingView
import com.example.movieapp.presentation.screen.movie_popular_feature.components.MovieItem
import com.example.movieapp.ui.theme.black
import kotlinx.coroutines.flow.flowOf

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    pagingMovies: LazyPagingItems<MovieSearch>,
    query: String,
    onSearch: (String) -> Unit,
    onEvent: (MovieSearchEvent) -> Unit,
    onDetail: (movieId: Int) -> Unit
) {

    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(black)
            .padding(paddingValues),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        SearchComponent(
            query = query,
            onSearch = {
                onSearch(it)
                isLoading = true
            },
            onQueryChangeEvent = { onEvent(it) },
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            items(pagingMovies.itemCount) { index ->
                val movie = pagingMovies[index]
                movie?.let {
                    MovieItem(
                        voteAverage = it.voteAverage,
                        imageUrl = it.imageUrl,
                        id = it.id,
                        onClick = { movieId ->
                            onDetail(movieId)
                        }
                    )
                    isLoading = false
                }
            }
            pagingMovies.apply {
                when {
                    isLoading -> {
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            LoadingView()
                        }
                    }

                    pagingMovies.loadState.refresh is LoadState.Error -> {
                        isLoading = false
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            ErrorScreen(
                                message = "Verifique sua conexão com a internet",
                                retry = { pagingMovies.retry() }
                            )
                        }
                    }

                    pagingMovies.loadState.append is LoadState.Error -> {
                        isLoading = false
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            ErrorScreen(
                                message = "Verifique sua conexão com a internet",
                                retry = { pagingMovies.retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchContentPreview() {
    val fakeMovies = listOf(
        MovieSearch(1, imageUrl = "https://image.tmdb.org/t/p/w500/poster1.jpg", voteAverage = 7.5),
        MovieSearch(2, imageUrl = "https://image.tmdb.org/t/p/w500/poster2.jpg", voteAverage = 8.2),
        MovieSearch(3, imageUrl = "https://image.tmdb.org/t/p/w500/poster3.jpg", voteAverage = 6.9)
    )

    SearchContent(
        modifier = Modifier,
        paddingValues = PaddingValues(0.dp),
        pagingMovies = fakeLazyPagingItems(fakeMovies),
        query = "",
        onSearch = {},
        onEvent = {},
        onDetail = {},
    )
}

@Composable
fun <T : Any> fakeLazyPagingItems(list: List<T>): LazyPagingItems<T> {
    return flowOf(PagingData.from(list)).collectAsLazyPagingItems()
}