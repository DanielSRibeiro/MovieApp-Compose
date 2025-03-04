package com.example.movieapp.presentation.screen.search_movie_feature

import androidx.paging.PagingData
import com.example.core.usecase.GetMovieSearchUseCase
import com.example.testing.MainCoroutineRule
import com.example.testing.model.MovieSearchFactory
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
class MovieSearchViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var getMovieSearchUseCase: GetMovieSearchUseCase

    private val viewModel by lazy {
        MovieSearchViewModel(getMovieSearchUseCase)
    }

    private val fakePagingDataMovies = PagingData.from(
        listOf(
            MovieSearchFactory().create(poster = MovieSearchFactory.Poster.Avengers),
            MovieSearchFactory().create(poster = MovieSearchFactory.Poster.JohnWick),
        )
    )

    @Test
    fun `should validate paging data object values when calling movie search paging data`() =
        runTest {
            // Arrange
            whenever(getMovieSearchUseCase.invoke(any())).thenReturn(
                flowOf(fakePagingDataMovies)
            )

            // Act
            viewModel.fetch("")
            val result = viewModel.uiState.movies.first()

            // Assert
            assertNotNull(result)
        }

    @Test(expected = RuntimeException::class)
    fun `should return exception when the calling to the use case returns an exception`() = runTest {
        // Arrange
        whenever(getMovieSearchUseCase.invoke(any())).thenThrow(
            RuntimeException()
        )
        // Act
        viewModel.fetch("")
    }

    @Test
    fun `should validate values when event is entered query`() = runTest {
        // Arrange
        val query = "Harry Potter"

        // Act
        viewModel.event(MovieSearchEvent.EnteredQuery(query))
        val result = viewModel.uiState.query

        // Assert
        assertEquals(result, query)
    }
}