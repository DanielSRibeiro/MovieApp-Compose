package com.example.movieapp.presentation.screen.movie_detail_feature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.cachedIn
import com.example.core.usecase.GetMovieDetailsUseCase
import com.example.core.usecase.base.ResultStatus
import com.example.movieapp.presentation.screen.movie_detail_feature.state.MovieDetailState
import com.example.movieapp.util.UtilFunctions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    var uiState by mutableStateOf(MovieDetailState())
        private set

    fun getMovieDetail(getMovieDetail: MovieDetailEvent.GetMovieDetail) {
        event(getMovieDetail)
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
        }
    }
}