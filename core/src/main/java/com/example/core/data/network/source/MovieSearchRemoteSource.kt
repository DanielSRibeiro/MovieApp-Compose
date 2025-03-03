package com.example.core.data.network.source

interface MovieSearchRemoteSource<T> {
    suspend fun getSearchMovies(page: Int, query: String): T
}