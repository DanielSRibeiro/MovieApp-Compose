package com.example.core.data.network.source

import com.example.core.domain.model.MoviePaging

interface MoviePopularRemoteDataSource {

    suspend fun getPopularMovies(page: Int): MoviePaging
}