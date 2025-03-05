package com.example.movieapp.framework.source

import com.example.core.data.network.source.MoviePopularRemoteDataSource
import com.example.core.domain.model.MoviePaging
import com.example.movieapp.framework.data.network.MovieService
import com.example.movieapp.framework.data.network.mapper.toMovie
import com.example.movieapp.framework.data.network.response.MovieResponse
import javax.inject.Inject

class MoviePopularRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MoviePopularRemoteDataSource {

    override suspend fun getPopularMovies(page: Int): MoviePaging {
        val response = service.getPopularMovies(page = page)
        
        return MoviePaging(
            page = response.page,
            totalPages = response.totalPages,
            totalResults = response.totalResults,
            movies = response.results.map { it.toMovie() }
        )
    }
}