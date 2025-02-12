package com.example.movieapp.framework.data.network.mapper

import com.example.core.domain.model.Movie
import com.example.movieapp.framework.data.network.response.MovieResultResponse
import com.example.movieapp.util.toPostURL

fun MovieResultResponse.toMovie(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        imageUrl = this.posterPath.toPostURL(),
        voteAverage = this.voteAverage,
    )
}