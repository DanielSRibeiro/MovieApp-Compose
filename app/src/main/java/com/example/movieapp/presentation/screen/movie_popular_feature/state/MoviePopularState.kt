package com.example.movieapp.presentation.screen.movie_popular_feature.state

import androidx.paging.PagingData
import com.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MoviePopularState(
    val movies: Flow<PagingData<Movie>> = emptyFlow()
)
