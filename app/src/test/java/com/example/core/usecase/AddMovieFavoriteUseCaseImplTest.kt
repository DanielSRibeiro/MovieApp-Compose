package com.example.core.usecase

import app.cash.turbine.test
import com.example.core.data.local.repository.MovieFavoriteRepository
import com.example.core.usecase.base.ResultStatus
import com.example.testing.MainCoroutineRule
import com.example.testing.model.MovieFactory
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AddMovieFavoriteUseCaseImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val addMovieFavoriteUseCase by lazy {
        AddMovieFavoriteUseCaseImpl(movieFavoriteRepository = movieFavoriteRepository)
    }

    @Test
    fun `should return Success from ResultStatus when the repository returns success equal to Unit`() = runTest {
        // Arrange
        whenever(movieFavoriteRepository.insert(movie)).thenReturn(Unit)

        // Act
        addMovieFavoriteUseCase.invoke(
            params = AddMovieFavoriteUseCase.Params(movie)
        ).test {
            // Assert
            assertEquals(ResultStatus.Loading, awaitItem())
            assertEquals(ResultStatus.Success(Unit), awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `should return Failure from ResultStatus when the repository an exception`() = runTest {
        // Arrange
        val exception = RuntimeException()
        whenever(movieFavoriteRepository.insert(movie)).thenThrow(exception)

        // Act
        addMovieFavoriteUseCase.invoke(
            params = AddMovieFavoriteUseCase.Params(movie)
        ).test {
            // Assert
            assertEquals(ResultStatus.Loading, awaitItem())
            assertEquals(ResultStatus.Failure(exception), awaitItem())
            awaitComplete()
        }
    }
}