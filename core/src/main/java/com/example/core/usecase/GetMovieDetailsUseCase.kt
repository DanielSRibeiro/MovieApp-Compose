package com.example.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.network.repository.MovieDetailsRepository
import com.example.core.domain.model.Movie
import com.example.core.domain.model.MovieDetail
import com.example.core.usecase.base.ResultStatus
import com.example.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetMovieDetailsUseCase {
    operator fun invoke(params: Params): Flow<ResultStatus<Pair<Flow<PagingData<Movie>>?, MovieDetail>>>
    data class Params(val movieId: Int, val pagingConfig: PagingConfig)
}

class GetPopularMoviesUseCaseImp @Inject constructor(
    private val repository: MovieDetailsRepository,
) : UseCase<GetMovieDetailsUseCase.Params, Pair<Flow<PagingData<Movie>>?, MovieDetail>>(),
    GetMovieDetailsUseCase {

    override suspend fun doWork(
        params: GetMovieDetailsUseCase.Params
    ): ResultStatus<Pair<Flow<PagingData<Movie>>?, MovieDetail>> {
        val pagingSource = repository.getMoviesSimilar(movieId = params.movieId)
        val movieDetail = repository.getMoviesDetails(params.movieId)
        val pager = Pager(
            config = params.pagingConfig,
            pagingSourceFactory = { pagingSource }
        ).flow

        return ResultStatus.Success(pager to movieDetail)
    }
}