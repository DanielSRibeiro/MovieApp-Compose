package com.example.core.usecase

import com.example.core.data.local.repository.MovieFavoriteRepository
import com.example.core.domain.model.Movie
import com.example.core.usecase.base.ResultStatus
import com.example.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AddMovieFavoriteUseCase {
    operator fun invoke(params: Params): Flow<ResultStatus<Unit>>
    data class Params(val movie: Movie)
}

class AddMovieFavoriteUseCaseImpl @Inject constructor(
    private val movieFavoriteRepository: MovieFavoriteRepository
) : UseCase<AddMovieFavoriteUseCase.Params, Unit>(), AddMovieFavoriteUseCase {

    override suspend fun doWork(params: AddMovieFavoriteUseCase.Params): ResultStatus<Unit> {
        val insert = movieFavoriteRepository.insert(params.movie)

        return ResultStatus.Success(insert)
    }
}