package com.example.movieapp.framework.source

import com.example.core.data.local.source.MovieFavoriteLocalDataSource
import com.example.core.domain.model.Movie
import com.example.movieapp.framework.data.local.dao.MovieDao
import com.example.movieapp.framework.data.local.mapper.toMovieEntity
import com.example.movieapp.framework.data.local.mapper.toMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieFavoriteLocalDataSourceImpl @Inject constructor(
    private val dao: MovieDao
) : MovieFavoriteLocalDataSource {

    override fun getMovies(): Flow<List<Movie>> {
        return dao.getMovies().map {
            it.toMovies()
        }
    }

    override suspend fun insert(movie: Movie) {
        dao.insertMovie(movieEntity = movie.toMovieEntity())
    }

    override suspend fun delete(movie: Movie) {
        dao.deleteMovie(movieEntity = movie.toMovieEntity())
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return dao.isFavorite(movieId = movieId) != null
    }
}