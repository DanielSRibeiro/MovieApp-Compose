package com.example.movieapp.presentation.screen.search_movie_feature

sealed class MovieSearchEvent {
    data class EnteredQuery(val value: String): MovieSearchEvent()
}