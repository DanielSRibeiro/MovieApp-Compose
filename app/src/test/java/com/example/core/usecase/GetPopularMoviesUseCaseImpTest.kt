package com.example.core.usecase

import androidx.paging.PagingConfig
import app.cash.turbine.test
import com.example.core.data.network.repository.MovieDetailsRepository
import com.example.core.usecase.base.ResultStatus
import com.example.testing.MainCoroutineRule
import com.example.testing.model.MovieDetailFactory
import com.example.testing.model.MovieFactory
import com.example.testing.model.PagingSourceMoviesFactory
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetPopularMoviesUseCaseImpTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: MovieDetailsRepository

    private val movieFactory = MovieFactory().create(poster = MovieFactory.Poster.Avengers)

    private val movieDetailFactory =
        MovieDetailFactory().create(poster = MovieDetailFactory.Poster.Avengers)

    private val pagingSourceFake = PagingSourceMoviesFactory().create(listOf(movieFactory))

    private val pagingConfig = PagingConfig(
        pageSize = 20,
        initialLoadSize = 20
    )

    private val useCase by lazy {
        GetPopularMoviesUseCaseImp(repository = repository)
    }

    @Test
    fun `should return Success from ResultStatus when get both requests returns success`() =
        runTest {
            // Arrange
            whenever(repository.getMoviesDetails(movieId = movieFactory.id))
                .thenReturn(movieDetailFactory)
            whenever(repository.getMoviesSimilar(movieId = movieFactory.id))
                .thenReturn(pagingSourceFake)

            // Act
            useCase.invoke(
                params = GetMovieDetailsUseCase.Params(
                    pagingConfig = pagingConfig,
                    movieId = movieFactory.id
                )
            ).test {
                // Assert
                Assert.assertEquals(ResultStatus.Loading, awaitItem())
                Assert.assertTrue(awaitItem() is ResultStatus.Success)

                verify(repository).getMoviesDetails(movieFactory.id)
                verify(repository).getMoviesSimilar(movieFactory.id)

                awaitComplete()
            }
        }

    @Test
    fun `should return Error from ResultStatus when get movieDetails request returns error`() =
        runTest {
            // Arrange
            val exception = RuntimeException()
            whenever(repository.getMoviesDetails(movieId = movieFactory.id))
                .thenThrow(exception)

            // Act
            useCase.invoke(
                params = GetMovieDetailsUseCase.Params(
                    pagingConfig = pagingConfig,
                    movieId = movieFactory.id
                )
            ).test {
                // Assert
                Assert.assertEquals(ResultStatus.Loading, awaitItem())
                Assert.assertTrue(awaitItem() is ResultStatus.Failure)

                verify(repository).getMoviesDetails(movieFactory.id)

                awaitComplete()
            }
        }

    @Test
    fun `should return a ResultStatus error when getting similar movies returns an error`() =
        runTest {
            // Arrange
            val exception = RuntimeException()
            whenever(repository.getMoviesSimilar(movieId = movieFactory.id))
                .thenThrow(exception)

            // Act
            useCase.invoke(
                params = GetMovieDetailsUseCase.Params(
                    pagingConfig = pagingConfig,
                    movieId = movieFactory.id
                )
            ).test {
                // Assert
                Assert.assertEquals(ResultStatus.Loading, awaitItem())
                Assert.assertTrue(awaitItem() is ResultStatus.Failure)

                verify(repository).getMoviesSimilar(movieFactory.id)

                awaitComplete()
            }
        }
}