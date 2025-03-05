package com.example.core.data.network.repository

import androidx.paging.PagingSource
import com.example.core.domain.model.MovieSearch

interface MovieSearchRepository {
    fun getSearchMovies(query: String) : PagingSource<Int, MovieSearch>
}