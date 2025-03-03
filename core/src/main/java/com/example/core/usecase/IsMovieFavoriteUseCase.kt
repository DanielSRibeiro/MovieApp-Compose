package com.example.core.usecase

import com.example.core.data.local.repository.MovieFavoriteRepository
import com.example.core.usecase.base.ResultStatus
import com.example.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IsMovieFavoriteUseCase {
    operator fun invoke(params: Params): Flow<ResultStatus<Boolean>>
    data class Params(val movieId: Int)
}

class IsMovieFavoriteUseCaseImpl @Inject constructor(
    private val movieFavoriteRepository: MovieFavoriteRepository
) : UseCase<IsMovieFavoriteUseCase.Params, Boolean>(), IsMovieFavoriteUseCase {

    override suspend fun doWork(params: IsMovieFavoriteUseCase.Params): ResultStatus<Boolean> {
        val isFavorite = movieFavoriteRepository.isFavorite(params.movieId)
        return ResultStatus.Success(isFavorite)
    }
}