package com.example.movieapp.framework.repository

import androidx.paging.PagingSource
import com.example.core.data.network.repository.MovieDetailsRepository
import com.example.core.data.network.source.MovieDetailsRemoteDataSource
import com.example.core.domain.model.Movie
import com.example.core.domain.model.MovieDetail
import com.example.movieapp.framework.paging.MovieSimilarPagingSource
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieDetailsRemoteDataSource
) : MovieDetailsRepository {

    override suspend fun getMoviesDetails(movieId: Int): MovieDetail {
        return remoteDataSource.getMoviesDetails(movieId)
    }

    override fun getMoviesSimilar(
        movieId: Int,
    ): PagingSource<Int, Movie> {
        return MovieSimilarPagingSource(remoteDataSource, movieId)
    }
}