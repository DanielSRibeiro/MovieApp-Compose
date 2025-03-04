package com.example.testing.model

import com.example.core.domain.model.Movie

class MovieFactory {

    fun create(poster: Poster) = when (poster) {
        Poster.Avengers -> Movie(
            id = 1,
            title = "Avengers",
            voteAverage = 7.1,
            imageUrl = "Url"
        )
        Poster.JohnWick -> Movie(
            id = 1,
            title = "Avengers",
            voteAverage = 7.1,
            imageUrl = "Url"
        )
    }

    sealed class Poster {
        data object Avengers: Poster()
        data object JohnWick: Poster()
    }
}