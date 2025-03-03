package com.example.movieapp.presentation.navigation

import com.example.movieapp.util.Constants.MOVIE_DETAIL_ARGUMENTS_KEY

sealed class Screens(val route: String) {
    data object DetailScreen : Screens(
        route = "movie_detail_destination?$MOVIE_DETAIL_ARGUMENTS_KEY=" +
                "{$MOVIE_DETAIL_ARGUMENTS_KEY}"
    ) {
        fun passMovieId(movieId: Int) =
            "movie_detail_destination?$MOVIE_DETAIL_ARGUMENTS_KEY=$movieId"
    }
}