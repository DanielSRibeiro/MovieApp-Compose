package com.example.core.domain.model

data class MovieDetail(
    val id: Int,
    val title: String,
    val genres: List<String>,
    val overview: String,
    val backdropPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val duration: Int = 0,
    val voteCount: Int = 0
)