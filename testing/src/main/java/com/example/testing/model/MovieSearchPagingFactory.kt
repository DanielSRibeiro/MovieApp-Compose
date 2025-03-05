package com.example.testing.model

import com.example.core.domain.model.MovieSearch
import com.example.core.domain.model.MovieSearchPaging

class MovieSearchPagingFactory {

    fun create() = MovieSearchPaging(
        page = 1,
        totalPages = 1,
        totalResults = 2,
        movies = listOf(
            MovieSearch(
                id = 1,
                voteAverage = 7.1,
                imageUrl = "Url"
            ),
            MovieSearch(
                id = 1,
                voteAverage = 7.1,
                imageUrl = "Url"
            )
        )
    )
}