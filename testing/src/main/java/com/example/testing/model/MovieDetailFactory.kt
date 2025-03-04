package com.example.testing.model

import com.example.core.domain.model.MovieDetail

class MovieDetailFactory {

    fun create(poster: Poster) = when (poster) {
        Poster.Avengers -> MovieDetail(
            id = 1,
            title = "Avengers",
            voteAverage = 7.1,
            genres = listOf("Action", "Adventure"),
            overview = "Overview",
            backdropPath = "Url",
            releaseDate = "04/05/2021",
            duration = 143,
            voteCount = 7
        )
        Poster.JohnWick -> MovieDetail(
            id = 2,
            title = "JohnWick",
            voteAverage = 7.1,
            genres = listOf("Action", "Adventure"),
            overview = "Overview",
            backdropPath = "Url",
            releaseDate = "04/05/2021",
            duration = 143,
            voteCount = 7
        )
    }

    sealed class Poster {
        data object Avengers: Poster()
        data object JohnWick: Poster()
    }
}