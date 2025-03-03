package com.example.movieapp.presentation.screen.movie_detail_feature

import com.example.core.domain.model.Movie

sealed class MovieDetailEvent {
    data class GetMovieDetail(val movieId: Int) : MovieDetailEvent()
    data class CheckedFavorite(val movieId: Int) : MovieDetailEvent()
    data class AddFavorite(val movie: Movie) : MovieDetailEvent()
    data class RemoveFavorite(val movie: Movie) : MovieDetailEvent()
}