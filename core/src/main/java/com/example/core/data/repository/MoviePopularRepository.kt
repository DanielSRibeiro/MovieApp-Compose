package com.example.core.data.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviePopularRepository {
    fun getPopularMovies(pagingConfig: PagingConfig): Flow<PagingData<Movie>>
}