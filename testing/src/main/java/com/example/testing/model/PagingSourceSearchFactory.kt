package com.example.testing.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.domain.model.MovieSearch

class PagingSourceSearchFactory {

    fun create(movies: List<MovieSearch>) = object : PagingSource<Int, MovieSearch>() {
        override fun getRefreshKey(state: PagingState<Int, MovieSearch>): Int = 1

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSearch> {
            return LoadResult.Page(
                data = movies,
                prevKey = null,
                nextKey = null
            )
        }
    }
}