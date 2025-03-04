package com.example.movieapp.presentation.screen.movie_favorite_feature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Movie
import com.example.core.usecase.GetMovieFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieFavoriteViewModel @Inject constructor(
    private val getMovieFavoriteUseCase: GetMovieFavoriteUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MovieFavoriteState())
    private set

    init {
        fetch()
    }

    private fun fetch() {
        viewModelScope.launch {
            val movies = getMovieFavoriteUseCase.invoke()
            uiState = uiState.copy(movies = movies)
        }
    }
}


data class MovieFavoriteState(
    val movies: Flow<List<Movie>> = emptyFlow()
)