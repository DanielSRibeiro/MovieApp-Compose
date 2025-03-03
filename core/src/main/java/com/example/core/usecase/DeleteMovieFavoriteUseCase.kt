package com.example.core.usecase

import com.example.core.data.local.repository.MovieFavoriteRepository
import com.example.core.domain.model.Movie
import com.example.core.usecase.base.ResultStatus
import com.example.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DeleteMovieFavoriteUseCase {
    operator fun invoke(params: Params): Flow<ResultStatus<Unit>>
    data class Params(val movie: Movie)
}

class DeleteMovieFavoriteUseCaseImpl @Inject constructor(
    private val movieFavoriteRepository: MovieFavoriteRepository
) : UseCase<DeleteMovieFavoriteUseCase.Params, Unit>(), DeleteMovieFavoriteUseCase {

    override suspend fun doWork(params: DeleteMovieFavoriteUseCase.Params): ResultStatus<Unit> {
        val delete = movieFavoriteRepository.delete(params.movie)
        return ResultStatus.Success(delete)
    }
}