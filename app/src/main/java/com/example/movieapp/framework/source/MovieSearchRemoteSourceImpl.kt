package com.example.movieapp.framework.source

import com.example.core.data.source.MovieSearchRemoteSource
import com.example.movieapp.framework.data.network.MovieService
import com.example.movieapp.framework.data.network.response.SearchResponse
import javax.inject.Inject

class MovieSearchRemoteSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieSearchRemoteSource<SearchResponse> {

    override suspend fun getSearchMovies(page: Int, query: String): SearchResponse {
        return service.searchMovies(page = page, query = query)
    }
}