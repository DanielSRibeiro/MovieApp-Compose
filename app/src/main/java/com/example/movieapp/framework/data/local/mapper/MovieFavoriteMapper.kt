package com.example.movieapp.framework.data.local.mapper

import com.example.core.domain.model.Movie
import com.example.movieapp.framework.data.local.entity.MovieEntity

fun List<MovieEntity>.toMovies() = map { movieEntity ->
    Movie(
        id = movieEntity.movieId,
        title = movieEntity.title,
        imageUrl = movieEntity.imageUrl
    )
}

fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        movieId = id,
        title = title,
        imageUrl = imageUrl
    )
}