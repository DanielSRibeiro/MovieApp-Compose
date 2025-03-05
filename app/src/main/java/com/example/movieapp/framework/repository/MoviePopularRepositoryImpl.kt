package com.example.movieapp.framework.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.network.repository.MoviePopularRepository
import com.example.core.data.network.source.MoviePopularRemoteDataSource
import com.example.core.domain.model.Movie
import com.example.movieapp.framework.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviePopularRepositoryImpl @Inject constructor(
    private val dataSource: MoviePopularRemoteDataSource
) : MoviePopularRepository {

    override fun getPopularMovies(pagingConfig: PagingConfig): Flow<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                MoviePagingSource(remoteDataSource = dataSource)
            }
        ).flow
    }
}