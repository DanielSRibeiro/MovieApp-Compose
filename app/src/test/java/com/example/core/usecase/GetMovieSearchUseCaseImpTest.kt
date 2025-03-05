package com.example.core.usecase

import androidx.paging.PagingConfig
import androidx.room.util.query
import com.example.core.data.network.repository.MoviePopularRepository
import com.example.core.data.network.repository.MovieSearchRepository
import com.example.testing.MainCoroutineRule
import com.example.testing.model.MovieFactory
import com.example.testing.model.MovieSearchFactory
import com.example.testing.model.PagingSourceMoviesFactory
import com.example.testing.model.PagingSourceSearchFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMovieSearchUseCaseImpTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: MovieSearchRepository

    private val movies = listOf(
        MovieSearchFactory().create(poster = MovieSearchFactory.Poster.Avengers),
    )

    private val pagingSourceFake = PagingSourceSearchFactory().create(movies)

    private val pagingConfig = PagingConfig(
        pageSize = 20,
        initialLoadSize = 20
    )

    private val getMovieSearchUseCase by lazy {
        GetMovieSearchUseCaseImp(repository = repository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() =
        runTest {
            // Arrange
            whenever(repository.getSearchMovies("")).thenReturn(pagingSourceFake)

            // Act
            val result = getMovieSearchUseCase.invoke(
                GetMovieSearchUseCase.GetMovieSearchParams(
                    query = "",
                    pagingConfig = pagingConfig
                )
            ).first()

            // Assert
            verify(repository).getSearchMovies("")
            assertNotNull(result)
        }

    @Test
    fun `should emit an empty stream when an exception is thrown when calling the invoke method`() =
        runTest {
            // Arrange
            val exception = RuntimeException()
            whenever(repository.getSearchMovies(any()))
                .thenThrow(exception)

            // Act
            val result = getMovieSearchUseCase.invoke(
                GetMovieSearchUseCase.GetMovieSearchParams(
                    query = "",
                    pagingConfig = pagingConfig
                )
            )

            // Assert
            verify(repository).getSearchMovies("")
        }
}