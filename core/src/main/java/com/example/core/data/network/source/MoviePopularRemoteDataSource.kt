package com.example.core.data.network.source

interface MoviePopularRemoteDataSource<T> {

    suspend fun getPopularMovies(page: Int): T
}