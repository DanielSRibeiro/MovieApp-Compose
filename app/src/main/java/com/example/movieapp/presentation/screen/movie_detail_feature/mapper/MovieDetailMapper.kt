package com.example.movieapp.presentation.screen.movie_detail_feature.mapper

import com.example.core.domain.model.Movie
import com.example.core.domain.model.MovieDetail

fun MovieDetail.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        voteAverage = voteAverage,
        imageUrl = backdropPath.toString()
    )
}