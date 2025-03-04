package com.example.movieapp.presentation.screen.movie_popular_feature

import androidx.paging.PagingData
import com.example.core.usecase.GetPopularMoviesUseCase
import com.example.testing.MainCoroutineRule
import com.example.testing.model.MovieFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class MoviePopularViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    private val viewModel by lazy {
        MoviePopularViewModel(getPopularMoviesUseCase)
    }

    private val fakePagingDataMovies = PagingData.from(
        listOf(
            MovieFactory().create(poster = MovieFactory.Poster.Avengers),
            MovieFactory().create(poster = MovieFactory.Poster.JohnWick),
        )
    )

    @Test
    fun `should validate paging data object values when calling paging data from movies`() =
        runTest {
            // Arrange
            whenever(getPopularMoviesUseCase.invoke(any())).thenReturn(
                flowOf(fakePagingDataMovies)
            )
            // Act
            val result = viewModel.uiState.movies.first()

            // Assert
            assertNotNull(result)
        }

    @Test(expected = RuntimeException::class)
    fun `should return exception when the calling to the use case returns an exception`() = runTest {
        // Arrange
        whenever(getPopularMoviesUseCase.invoke(any())).thenThrow(
            RuntimeException()
        )
        // Act
        viewModel.uiState.movies.first()
    }
}