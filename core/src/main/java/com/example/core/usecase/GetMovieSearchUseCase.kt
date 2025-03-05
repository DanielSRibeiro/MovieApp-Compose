package com.example.core.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.network.repository.MovieSearchRepository
import com.example.core.domain.model.MovieSearch
import com.example.core.usecase.base.PagingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

interface GetMovieSearchUseCase {
    operator fun invoke(params: GetMovieSearchParams): Flow<PagingData<MovieSearch>>
    data class GetMovieSearchParams(val query: String, val pagingConfig: PagingConfig)
}

class GetMovieSearchUseCaseImp @Inject constructor(
    private val repository: MovieSearchRepository
) : GetMovieSearchUseCase,
    PagingUseCase<GetMovieSearchUseCase.GetMovieSearchParams, MovieSearch>() {

        override fun createFlowObservable(params: GetMovieSearchUseCase.GetMovieSearchParams): Flow<PagingData<MovieSearch>> {
        return try {
            val pagingSource = repository.getSearchMovies(query = params.query)

            return Pager(
                config = params.pagingConfig,
                pagingSourceFactory = {
                    pagingSource
                }
            ).flow
        } catch (e: Exception){
            emptyFlow()
        }
    }
}