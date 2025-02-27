package com.example.core.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.repository.MovieSearchRepository
import com.example.core.domain.model.MovieSearch
import com.example.core.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetMovieSearchUseCase {
    operator fun invoke(params: GetMovieSearchParams): Flow<PagingData<MovieSearch>>
    data class GetMovieSearchParams(val query: String)
}

class GetMovieSearchUseCaseImp @Inject constructor(
    private val repository: MovieSearchRepository
) : GetMovieSearchUseCase,
    PagingUseCase<GetMovieSearchUseCase.GetMovieSearchParams, MovieSearch>() {

        override fun createFlowObservable(params: GetMovieSearchUseCase.GetMovieSearchParams): Flow<PagingData<MovieSearch>> {
        return repository.getPopularMovies(
            query = params.query,
            pagingConfig = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
            )
        )
    }
}