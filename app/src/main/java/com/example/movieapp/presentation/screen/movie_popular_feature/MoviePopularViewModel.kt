package com.example.movieapp.presentation.screen.movie_popular_feature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.core.usecase.GetPopularMoviesUseCase
import com.example.movieapp.presentation.screen.movie_popular_feature.state.MoviePopularState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviePopularViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MoviePopularState())
        private set

    init {
        val movies = getPopularMoviesUseCase.invoke(
            GetPopularMoviesUseCase.GetPopularMoviesParams(
                PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        ).cachedIn(viewModelScope)

        uiState = uiState.copy(movies = movies)
    }
}