package com.example.movieapp.framework.repository

import androidx.paging.PagingSource
import com.example.core.data.network.repository.MoviePopularRepository
import com.example.core.data.network.source.MoviePopularRemoteDataSource
import com.example.core.domain.model.Movie
import com.example.movieapp.framework.paging.MoviePagingSource
import javax.inject.Inject

class MoviePopularRepositoryImpl @Inject constructor(
    private val dataSource: MoviePopularRemoteDataSource
) : MoviePopularRepository {

    override fun getPopularMovies(): PagingSource<Int, Movie> {
        return MoviePagingSource(remoteDataSource = dataSource)
    }
}