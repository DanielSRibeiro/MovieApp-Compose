package com.example.core.data.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow

interface MovieSearchRepository {
    fun getPopularMovies(query: String, pagingConfig: PagingConfig) : Flow<PagingData<MovieSearch>>
}