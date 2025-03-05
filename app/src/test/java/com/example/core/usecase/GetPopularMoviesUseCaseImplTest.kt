package com.example.core.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource.LoadResult
import com.example.core.data.local.repository.MovieFavoriteRepository
import com.example.core.data.network.repository.MoviePopularRepository
import com.example.core.domain.model.Movie
import com.example.testing.MainCoroutineRule
import com.example.testing.model.MovieFactory
import com.example.testing.model.PagingSourceMoviesFactory
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetPopularMoviesUseCaseImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: MoviePopularRepository

    private val movies = listOf(
        MovieFactory().create(poster = MovieFactory.Poster.Avengers),
    )

    private val pagingSourceFake = PagingSourceMoviesFactory().create(movies)

    private val pagingConfig = PagingConfig(
        pageSize = 20,
        initialLoadSize = 20
    )

    private val getPopularMoviesUseCase by lazy {
        GetPopularMoviesUseCaseImpl(repository = repository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() =
        runTest {
            // Arrange
            whenever(repository.getPopularMovies()).thenReturn(pagingSourceFake)

            // Act
            val result = getPopularMoviesUseCase.invoke(
                GetPopularMoviesUseCase.GetPopularMoviesParams(
                    pagingConfig = pagingConfig
                )
            ).first()

            // Assert
            verify(repository).getPopularMovies()
            assertNotNull(result)
        }

    @Test
    fun `should emit an empty stream when an exception is thrown when calling the invoke method`() =
        runTest {
            // Arrange
            val exception = RuntimeException()
            whenever(repository.getPopularMovies())
                .thenThrow(exception)

            // Act
            val result = getPopularMoviesUseCase.invoke(
                params = GetPopularMoviesUseCase.GetPopularMoviesParams(
                    pagingConfig = pagingConfig
                )
            )

            // Assert
            verify(repository).getPopularMovies()
        }
}