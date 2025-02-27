package com.example.core.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.data.repository.MovieDetailsRepository
import com.example.core.domain.model.Movie
import com.example.core.domain.model.MovieDetail
import com.example.core.usecase.base.ResultStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

interface GetMovieDetailsUseCase {
    operator fun invoke(params: Params): Flow<ResultStatus<Pair<Flow<PagingData<Movie>>?, MovieDetail>>>
    data class Params(val movieId: Int)
}

class GetPopularMoviesUseCaseImp @Inject constructor(
    private val repository: MovieDetailsRepository,
) :
    GetMovieDetailsUseCase {

    override fun invoke(params: GetMovieDetailsUseCase.Params): Flow<ResultStatus<Pair<Flow<PagingData<Movie>>?, MovieDetail>>> {
        return flow {
            try {
                emit(ResultStatus.Loading)
                val movieDetail = repository.getMoviesDetails(params.movieId)
                val movieSimilar =
                    repository.getMoviesSimilar(
                        movieId = params.movieId,
                        pagingConfig = PagingConfig(
                            pageSize = 20,
                            initialLoadSize = 20
                        )
                    )
                emit(ResultStatus.Success(movieSimilar to movieDetail))
            } catch (e: IOException) {
                emit(ResultStatus.Failure(e))
            } catch (e: HttpException) {
                emit(ResultStatus.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}