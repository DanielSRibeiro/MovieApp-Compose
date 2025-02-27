package com.example.movieapp.presentation.screen.movie_detail_feature.state

import androidx.compose.ui.graphics.Color
import androidx.paging.PagingData
import com.example.core.domain.model.Movie
import com.example.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieDetailState(
    val movieDetail: MovieDetail? = null,
    val error: String? = "",
    val isLoading: Boolean = false,
    val iconColor: Color = Color.White,
    val results: Flow<PagingData<Movie>> = emptyFlow()
)