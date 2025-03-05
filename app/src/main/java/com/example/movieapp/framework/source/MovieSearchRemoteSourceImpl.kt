package com.example.movieapp.framework.source

import com.example.core.data.network.source.MovieSearchRemoteSource
import com.example.core.domain.model.MovieSearchPaging
import com.example.movieapp.framework.data.network.MovieService
import com.example.movieapp.framework.data.network.mapper.toMovieSearch
import com.example.movieapp.framework.data.network.response.SearchResponse
import javax.inject.Inject

class MovieSearchRemoteSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieSearchRemoteSource {

    override suspend fun getSearchMovies(page: Int, query: String): MovieSearchPaging {
        val response = service.searchMovies(page = page, query = query)
        return MovieSearchPaging(
            page = response.page,
            totalPages = response.totalPages,
            totalResults = response.totalResults,
            movies = response.results.map { it.toMovieSearch() }
        )
    }
}