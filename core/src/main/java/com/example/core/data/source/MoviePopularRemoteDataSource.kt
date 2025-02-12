package com.example.core.data.source

interface MoviePopularRemoteDataSource<T> {

    suspend fun getPopularMovies(page: Int): T
}