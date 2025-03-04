package com.example.movieapp.presentation.screen.movie_detail_feature

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import com.example.core.usecase.AddMovieFavoriteUseCase
import com.example.core.usecase.DeleteMovieFavoriteUseCase
import com.example.core.usecase.GetMovieDetailsUseCase
import com.example.core.usecase.IsMovieFavoriteUseCase
import com.example.core.usecase.base.ResultStatus
import com.example.movieapp.presentation.screen.movie_detail_feature.mapper.toMovie
import com.example.movieapp.util.Constants
import com.example.testing.MainCoroutineRule
import com.example.testing.model.MovieDetailFactory
import com.example.testing.model.MovieFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class MovieDetailViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var getMovieDetailUseCase: GetMovieDetailsUseCase

    @Mock
    lateinit var addFavoriteUseCase: AddMovieFavoriteUseCase

    @Mock
    lateinit var removeFavoriteUseCase: DeleteMovieFavoriteUseCase

    @Mock
    lateinit var isMovieFavoriteUseCase: IsMovieFavoriteUseCase

    private val movieDetailFactory =
        MovieDetailFactory().create(poster = MovieDetailFactory.Poster.Avengers)

    private val movie = movieDetailFactory.toMovie()

    private val pagingData = PagingData.from(
        listOf(
            MovieFactory().create(poster = MovieFactory.Poster.Avengers),
            MovieFactory().create(poster = MovieFactory.Poster.JohnWick),
        )
    )

    private val savedStateHandle = SavedStateHandle(
        mapOf(Constants.MOVIE_DETAIL_ARGUMENTS_KEY to movieDetailFactory.id)
    )

    private val viewModel by lazy {
        MovieDetailViewModel(
            getMovieDetailUseCase,
            addFavoriteUseCase,
            removeFavoriteUseCase,
            isMovieFavoriteUseCase,
            savedStateHandle = savedStateHandle
        )
    }

    @Test
    fun `should notify uiState with success when get movies similar and movie details return success`() =
        runTest {
            whenever(getMovieDetailUseCase.invoke(any()))
                .thenReturn(flowOf(ResultStatus.Success(flowOf(pagingData) to movieDetailFactory)))

            whenever(isMovieFavoriteUseCase.invoke(any()))
                .thenReturn(flowOf())

            val argumentCaptor = argumentCaptor<GetMovieDetailsUseCase.Params>()

            viewModel.uiState.isLoading

            verify(getMovieDetailUseCase).invoke(argumentCaptor.capture())
            assertEquals(movieDetailFactory.id, argumentCaptor.firstValue.movieId)
            val movieDetails = viewModel.uiState.movieDetail
            val results = viewModel.uiState.results

            assertNotNull(movieDetails)
            assertNotNull(results)
        }

    @Test
    fun `should notify uiState with error when get movies similar return exception`() = runTest {
        val exception = Exception("Um erro ocorreu")
        whenever(getMovieDetailUseCase.invoke(any())).thenReturn(
            flowOf(ResultStatus.Failure(exception))
        )

        whenever(isMovieFavoriteUseCase.invoke(any()))
            .thenReturn(flowOf())

        viewModel.uiState.isLoading

        val error = viewModel.uiState.error
        assertEquals(exception.message, error)
    }

    @Test
    fun `should call delete favorite and notify uiState with filled favorite icon when current icon is checked`() =
        runTest {
            // Arrange
            whenever(removeFavoriteUseCase.invoke(any())).thenReturn(
                flowOf(ResultStatus.Success(Unit))
            )
            whenever(isMovieFavoriteUseCase.invoke(any())).thenReturn(
                flowOf(ResultStatus.Success(true))
            )
            whenever(getMovieDetailUseCase.invoke(any())).thenReturn(flowOf())

            val deleteArgumentCaptor = argumentCaptor<DeleteMovieFavoriteUseCase.Params>()
            val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

            // Act
            viewModel.favorite(movie = movie)

            // Assert
            verify(removeFavoriteUseCase).invoke(deleteArgumentCaptor.capture())
            assertEquals(movie, deleteArgumentCaptor.firstValue.movie)

            verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
            assertEquals(movie.id, checkedArgumentCaptor.firstValue.movieId)

            val iconColor = viewModel.uiState.iconColor
            assertEquals(iconColor, Color.White)
        }

    @Test
    fun `should notify uiState with filled favorite icon when current icon is not checked`() =
        runTest {
            // Arrange
            whenever(addFavoriteUseCase.invoke(any())).thenReturn(
                flowOf(ResultStatus.Success(Unit))
            )
            whenever(isMovieFavoriteUseCase.invoke(any())).thenReturn(
                flowOf(ResultStatus.Success(false))
            )
            whenever(getMovieDetailUseCase.invoke(any())).thenReturn(flowOf())

            val addArgumentCaptor = argumentCaptor<AddMovieFavoriteUseCase.Params>()
            val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

            // Act
            viewModel.favorite(movie = movie)


            // Assert
            verify(addFavoriteUseCase).invoke(addArgumentCaptor.capture())
            assertEquals(movie, addArgumentCaptor.firstValue.movie)

            verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
            assertEquals(movie.id, checkedArgumentCaptor.firstValue.movieId)

            val iconColor = viewModel.uiState.iconColor
            assertEquals(iconColor, Color.Red)
        }

    @Test
    fun `should notify uiState with bookmark icon filled in if bookmark check returns true`() =
        runTest {
            // Arrange
            whenever(isMovieFavoriteUseCase.invoke(any())).thenReturn(
                flowOf(ResultStatus.Success(true))
            )
            whenever(getMovieDetailUseCase.invoke(any())).thenReturn(flowOf())

            val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

            // Act
            viewModel.uiState.isLoading

            verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
            assertEquals(movie.id, checkedArgumentCaptor.firstValue.movieId)

            val iconColor = viewModel.uiState.iconColor
            assertEquals(iconColor, Color.Red)
        }

    @Test
    fun `should notify uiState with bookmark icon filled in if bookmark check returns false`() =
        runTest {
            // Arrange
            whenever(isMovieFavoriteUseCase.invoke(any())).thenReturn(
                flowOf(ResultStatus.Success(false))
            )
            whenever(getMovieDetailUseCase.invoke(any())).thenReturn(flowOf())

            val checkedArgumentCaptor = argumentCaptor<IsMovieFavoriteUseCase.Params>()

            // Act
            viewModel.uiState.isLoading

            verify(isMovieFavoriteUseCase).invoke(checkedArgumentCaptor.capture())
            assertEquals(movie.id, checkedArgumentCaptor.firstValue.movieId)

            val iconColor = viewModel.uiState.iconColor
            assertEquals(iconColor, Color.White)
        }
}