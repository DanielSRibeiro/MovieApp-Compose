package com.example.movieapp.framework.source

import com.example.core.data.source.MovieDetailsRemoteDataSource
import com.example.core.domain.model.MovieDetail
import com.example.movieapp.framework.data.network.MovieService
import com.example.movieapp.framework.data.network.response.MovieResponse
import com.example.movieapp.util.toBackdropURL
import javax.inject.Inject

class MovieDetailsRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
) : MovieDetailsRemoteDataSource<MovieResponse> {

    override suspend fun getMoviesDetails(movieId: Int): MovieDetail {
        val response = service.getMovie(movieId = movieId)
        val genres = response.genres.map { it.name }
        return MovieDetail(
            id = response.id,
            title = response.title,
            genres = genres,
            overview = response.overview,
            backdropPath = response.backdropPath.toBackdropURL(),
            releaseDate = response.releaseDate,
            voteAverage = response.voteAverage,
            duration = response.runtime,
            voteCount = response.voteCount
        )
    }

    override suspend fun getMoviesSimilar(page: Int, movieId: Int): MovieResponse {
        return service.getMoviesSimilar(page = page, movieId = movieId)
    }
}