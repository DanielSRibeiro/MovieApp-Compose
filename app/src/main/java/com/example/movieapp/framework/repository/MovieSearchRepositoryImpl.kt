package com.example.movieapp.framework.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.network.repository.MovieSearchRepository
import com.example.core.data.network.source.MovieSearchRemoteSource
import com.example.core.domain.model.MovieSearch
import com.example.movieapp.framework.data.network.response.SearchResponse
import com.example.movieapp.framework.paging.MovieSearchPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieSearchRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieSearchRemoteSource<SearchResponse>
) : MovieSearchRepository {

    override fun getPopularMovies(
        query: String,
        pagingConfig: PagingConfig
    ): Flow<PagingData<MovieSearch>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                MovieSearchPagingSource(query = query, remoteDataSource = remoteDataSource)
            }
        ).flow
    }
}