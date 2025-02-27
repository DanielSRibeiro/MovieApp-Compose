package com.example.movieapp.presentation.screen.movie_detail_feature

sealed class MovieDetailEvent {
    data class GetMovieDetail(val movieId: Int) : MovieDetailEvent()
}