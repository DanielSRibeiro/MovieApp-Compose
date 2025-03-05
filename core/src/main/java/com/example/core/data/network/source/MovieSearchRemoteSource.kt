package com.example.core.data.network.source

import com.example.core.domain.model.MovieSearchPaging

interface MovieSearchRemoteSource {
    suspend fun getSearchMovies(page: Int, query: String): MovieSearchPaging
}