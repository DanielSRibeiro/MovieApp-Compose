package com.example.core.data.network.repository

import androidx.paging.PagingSource
import com.example.core.domain.model.Movie

interface MoviePopularRepository {
    fun getPopularMovies(): PagingSource<Int, Movie>
}