package com.example.core.domain.model

data class MovieSearchPaging(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val movies: List<MovieSearch>,
)
