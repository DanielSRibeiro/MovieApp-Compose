package com.example.movieapp.presentation.screen.movie_favorite_feature

import com.example.core.usecase.GetMovieFavoriteUseCase
import com.example.testing.MainCoroutineRule
import com.example.testing.model.MovieFactory
import com.google.common.base.Verify
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
class MovieFavoriteViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var getMovieFavoriteUseCase: GetMovieFavoriteUseCase

    private val viewModel by lazy {
        MovieFavoriteViewModel(getMovieFavoriteUseCase)
    }

    private val fakeMovies = listOf(
        MovieFactory().create(poster = MovieFactory.Poster.Avengers),
        MovieFactory().create(poster = MovieFactory.Poster.JohnWick),
    )


    @Test
    fun `should validate the data object values when calling list of favorites`() = runTest {
        whenever(getMovieFavoriteUseCase.invoke()).thenReturn(
            flowOf(fakeMovies)
        )

        val result = viewModel.uiState.movies.first()

        assertNotNull(result)
        Verify.verify(result.containsAll(fakeMovies))
    }
}