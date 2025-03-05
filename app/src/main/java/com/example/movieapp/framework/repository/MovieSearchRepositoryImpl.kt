package com.example.movieapp.framework.repository

import androidx.paging.PagingSource
import com.example.core.data.network.repository.MovieSearchRepository
import com.example.core.data.network.source.MovieSearchRemoteSource
import com.example.core.domain.model.MovieSearch
import com.example.movieapp.framework.paging.MovieSearchPagingSource
import javax.inject.Inject

class MovieSearchRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieSearchRemoteSource
) : MovieSearchRepository {

    override fun getSearchMovies(
        query: String
    ): PagingSource<Int, MovieSearch> {
        return MovieSearchPagingSource(query, remoteDataSource)
    }
}