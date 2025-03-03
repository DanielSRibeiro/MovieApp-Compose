package com.example.movieapp.framework.source

import com.example.core.data.network.source.MoviePopularRemoteDataSource
import com.example.movieapp.framework.data.network.MovieService
import com.example.movieapp.framework.data.network.response.MovieResponse
import javax.inject.Inject

class MoviePopularRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MoviePopularRemoteDataSource<MovieResponse> {

    override suspend fun getPopularMovies(page: Int): MovieResponse {
        return service.getPopularMovies(page = page)
    }
}