package com.example.testing.model

import com.example.core.domain.model.Movie
import com.example.core.domain.model.MoviePaging

class MoviePagingFactory {

    fun create() = MoviePaging(
        page = 1,
        totalPages = 1,
        totalResults = 2,
        movies = listOf(
            Movie(
                id = 1,
                title = "Avengers",
                voteAverage = 7.1,
                imageUrl = "Url"
            ), Movie(
                id = 1,
                title = "Avengers",
                voteAverage = 7.1,
                imageUrl = "Url"
            )
        )
    )
}