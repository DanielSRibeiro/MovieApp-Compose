package com.example.core.data.source

import com.example.core.domain.model.MovieDetail

interface MovieDetailsRemoteDataSource<T> {
    suspend fun getMoviesDetails(movieId: Int) : MovieDetail
    suspend fun getMoviesSimilar(page: Int, movieId: Int) : T
}