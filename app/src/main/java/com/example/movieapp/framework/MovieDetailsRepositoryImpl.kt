package com.example.movieapp.framework

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.repository.MovieDetailsRepository
import com.example.core.data.source.MovieDetailsRemoteDataSource
import com.example.core.domain.model.Movie
import com.example.core.domain.model.MovieDetail
import com.example.movieapp.framework.data.network.response.MovieResponse
import com.example.movieapp.framework.paging.MovieSimilarPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieDetailsRemoteDataSource<MovieResponse>
) : MovieDetailsRepository {

    override suspend fun getMoviesDetails(movieId: Int): MovieDetail {
        return remoteDataSource.getMoviesDetails(movieId)
    }

    override suspend fun getMoviesSimilar(
        movieId: Int,
        pagingConfig: PagingConfig
    ): Flow<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                MovieSimilarPagingSource(
                    movieId = movieId,
                    remoteDataSource = remoteDataSource
                )
            }
        ).flow
    }
}