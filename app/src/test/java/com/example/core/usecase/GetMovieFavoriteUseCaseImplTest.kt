package com.example.core.usecase

import com.example.core.data.local.repository.MovieFavoriteRepository
import com.example.core.domain.model.Movie
import com.example.testing.MainCoroutineRule
import com.example.testing.model.MovieFactory
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetMovieFavoriteUseCaseImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var movieFavoriteRepository: MovieFavoriteRepository

    private val movies = listOf(
        MovieFactory().create(poster = MovieFactory.Poster.Avengers),
        MovieFactory().create(poster = MovieFactory.Poster.JohnWick)
    )

    private val getMovieFavoriteUseCase by lazy {
        GetMovieFavoriteUseCaseImpl(movieFavoriteRepository = movieFavoriteRepository)
    }

    @Test
    fun `should return Success from ResultStatus when the repository returns a list of movies`() =
        runTest {
            // Arrange
            whenever(movieFavoriteRepository.getMovies()).thenReturn(
                flowOf(movies)
            )

            // Act
            val result = getMovieFavoriteUseCase.invoke().first()

            // Assert
            assertEquals(result[0].id, movies[0].id)
            verify(movieFavoriteRepository).getMovies()
        }

    @Test
    fun `should return Failure from ResultStatus when the repository an exception`() = runTest {
        // Arrange
        val exception = RuntimeException()
        whenever(movieFavoriteRepository.getMovies()).thenThrow(exception)

        // Act
        val result = getMovieFavoriteUseCase.invoke()

        // Assert
        assertEquals(result, emptyFlow<Movie>())
    }
}