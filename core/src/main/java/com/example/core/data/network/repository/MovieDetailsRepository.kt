package com.example.core.data.network.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.domain.model.Movie
import com.example.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {
    suspend fun getMoviesDetails(movieId: Int): MovieDetail
    suspend fun getMoviesSimilar(movieId: Int, pagingConfig: PagingConfig): Flow<PagingData<Movie>>
}