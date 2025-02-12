package com.example.movieapp.framework

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.repository.MoviePopularRepository
import com.example.core.data.source.MoviePopularRemoteDataSource
import com.example.core.domain.model.Movie
import com.example.movieapp.framework.data.network.response.MovieResponse
import com.example.movieapp.framework.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviePopularRepositoryImpl @Inject constructor(
    private val dataSource: MoviePopularRemoteDataSource<MovieResponse>
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