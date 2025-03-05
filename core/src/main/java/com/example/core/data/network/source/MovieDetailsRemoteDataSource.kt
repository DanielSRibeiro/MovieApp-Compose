package com.example.core.data.network.source

import com.example.core.domain.model.MovieDetail
import com.example.core.domain.model.MoviePaging

interface MovieDetailsRemoteDataSource {
    suspend fun getMoviesDetails(movieId: Int) : MovieDetail
    suspend fun getMoviesSimilar(page: Int, movieId: Int) : MoviePaging
}