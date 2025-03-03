package com.example.core.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.network.repository.MoviePopularRepository
import com.example.core.domain.model.Movie
import com.example.core.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPopularMoviesUseCase {
    fun invoke(params: GetPopularMoviesParams): Flow<PagingData<Movie>>

    data class GetPopularMoviesParams(val pagingConfig: PagingConfig)
}

class GetPopularMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviePopularRepository
): GetPopularMoviesUseCase,
    PagingUseCase<GetPopularMoviesUseCase.GetPopularMoviesParams, Movie>() {


    override fun createFlowObservable(
        params: GetPopularMoviesUseCase.GetPopularMoviesParams
    ): Flow<PagingData<Movie>> {
        return repository.getPopularMovies(
            pagingConfig = params.pagingConfig
        )
    }
}