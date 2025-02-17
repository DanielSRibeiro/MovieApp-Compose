package com.example.core.data.source

interface MovieSearchRemoteSource<T> {
    suspend fun getSearchMovies(page: Int, query: String): T
}