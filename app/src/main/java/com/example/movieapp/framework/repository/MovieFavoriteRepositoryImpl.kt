package com.example.movieapp.framework.repository

import com.example.core.data.local.repository.MovieFavoriteRepository
import com.example.core.data.local.source.MovieFavoriteLocalDataSource
import com.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieFavoriteRepositoryImpl @Inject constructor(
    private val localDataSource: MovieFavoriteLocalDataSource
) : MovieFavoriteRepository {

    override fun getMovies(): Flow<List<Movie>> {
        return localDataSource.getMovies()
    }

    override suspend fun insert(movie: Movie) {
        localDataSource.insert(movie)
    }

    override suspend fun delete(movie: Movie) {
        localDataSource.delete(movie)
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return localDataSource.isFavorite(movieId)
    }
}