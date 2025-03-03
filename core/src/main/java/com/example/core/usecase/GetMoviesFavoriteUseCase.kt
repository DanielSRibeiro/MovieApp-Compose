package com.example.core.usecase

import com.example.core.data.local.repository.MovieFavoriteRepository
import com.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetMovieFavoriteUseCase {
    operator fun invoke(): Flow<List<Movie>>
}

class GetMovieFavoriteUseCaseImpl @Inject constructor(
    private val movieFavoriteRepository: MovieFavoriteRepository
) : GetMovieFavoriteUseCase {

    override fun invoke(): Flow<List<Movie>> {
        return movieFavoriteRepository.getMovies()
    }

}