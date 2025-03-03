package com.example.movieapp.presentation.screen.movie_detail_feature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Movie
import com.example.core.usecase.AddMovieFavoriteUseCase
import com.example.core.usecase.DeleteMovieFavoriteUseCase
import com.example.core.usecase.GetMovieDetailsUseCase
import com.example.core.usecase.GetMovieFavoriteUseCase
import com.example.core.usecase.IsMovieFavoriteUseCase
import com.example.core.usecase.base.ResultStatus
import com.example.movieapp.presentation.screen.movie_detail_feature.state.MovieDetailState
import com.example.movieapp.util.UtilFunctions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val addFavoriteUseCase: AddMovieFavoriteUseCase,
    private val removeFavoriteUseCase: DeleteMovieFavoriteUseCase,
    private val isMovieFavoriteUseCase: IsMovieFavoriteUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MovieDetailState())
        private set

    fun checkedFavorite(checkedFavoriteUseCase: MovieDetailEvent.CheckedFavorite) {
        event(checkedFavoriteUseCase)
    }

    fun getMovieDetail(getMovieDetail: MovieDetailEvent.GetMovieDetail) {
        event(getMovieDetail)
    }

    fun favorite(movie: Movie) {
        if(uiState.iconColor == Color.White) {
            event(MovieDetailEvent.AddFavorite(movie))
        } else {
            event(MovieDetailEvent.RemoveFavorite(movie))
        }
    }

    private fun event(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.GetMovieDetail -> {
                viewModelScope.launch {
                    getMovieDetailsUseCase.invoke(
                        params = GetMovieDetailsUseCase.Params(
                            movieId = event.movieId,
                        )
                    ).collect { result ->
                        when (result) {
                            is ResultStatus.Success -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    movieDetail = result.data.second,
                                    results = result.data.first ?: emptyFlow()
                                )
                            }

                            is ResultStatus.Failure -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    error = result.throwable.message.toString(),
                                )
                                UtilFunctions.logError(
                                    "DETAIL-ERROR",
                                    result.throwable.message.toString()
                                )
                            }

                            ResultStatus.Loading -> {
                                uiState = uiState.copy(
                                    isLoading = true,
                                )
                            }

                        }
                    }
                }
            }

            is MovieDetailEvent.AddFavorite -> {
                viewModelScope.launch {
                    addFavoriteUseCase.invoke(
                        params = AddMovieFavoriteUseCase.Params(
                            movie = event.movie
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is ResultStatus.Success -> {
                                uiState = uiState.copy(
                                    iconColor = Color.Red
                                )
                            }

                            is ResultStatus.Failure ->
                                UtilFunctions.logError("DETAIL", "Error ao cadastrar filme")

                            ResultStatus.Loading -> {}
                        }
                    }
                }
            }

            is MovieDetailEvent.CheckedFavorite -> {
                viewModelScope.launch {
                    isMovieFavoriteUseCase.invoke(
                        params = IsMovieFavoriteUseCase.Params(
                            movieId = event.movieId
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is ResultStatus.Failure ->
                                UtilFunctions.logError("DETAIL", "um erro ocorreu")

                            ResultStatus.Loading -> {}
                            is ResultStatus.Success -> {
                                uiState = if (result.data) {
                                    uiState.copy(iconColor = Color.Red)
                                } else {
                                    uiState.copy(iconColor = Color.White)
                                }
                            }
                        }
                    }
                }
            }

            is MovieDetailEvent.RemoveFavorite -> {
                viewModelScope.launch {
                    removeFavoriteUseCase.invoke(
                        params = DeleteMovieFavoriteUseCase.Params(
                            movie = event.movie
                        )
                    ).collectLatest { result ->
                        when (result) {
                            is ResultStatus.Success -> {
                                uiState = uiState.copy(
                                    iconColor = Color.White
                                )
                            }

                            is ResultStatus.Failure -> UtilFunctions.logError(
                                "DETAIL",
                                "Error ao favoritar"
                            )

                            ResultStatus.Loading -> {}
                        }
                    }
                }

            }
        }
    }
}