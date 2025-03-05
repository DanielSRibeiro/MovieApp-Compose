package com.example.movieapp.framework.paging

import androidx.paging.PagingSource
import com.example.core.data.network.source.MoviePopularRemoteDataSource
import com.example.core.domain.model.Movie
import com.example.testing.MainCoroutineRule
import com.example.testing.model.MovieFactory
import com.example.testing.model.MoviePagingFactory
import com.nhaarman.mockitokotlin2.any
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
class MoviePagingSourceTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var remoteDataSource: MoviePopularRemoteDataSource

    private val moveFactory = MovieFactory()

    private val moviePagingFactory = MoviePagingFactory().create()

    private val moviePagingSource by lazy {
        MoviePagingSource(remoteDataSource)
    }

    @Test
    fun `should return the correct data when load is called`() = runTest {
        whenever(remoteDataSource.getPopularMovies(any()))
            .thenReturn(moviePagingFactory)

        val result = moviePagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        val resultExpected = listOf(
            moveFactory.create(MovieFactory.Poster.Avengers),
            moveFactory.create(MovieFactory.Poster.JohnWick)
        )

        assertEquals(
            PagingSource.LoadResult.Page(
                data = resultExpected,
                prevKey = null,
                nextKey = null
            ),
            result
        )
    }

    @Test
    fun `should return a error load result when load is called `() = runTest {
        val exception = RuntimeException()
        whenever(remoteDataSource.getPopularMovies(any()))
            .thenThrow(exception)

        val result = moviePagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertEquals(
            PagingSource.LoadResult.Error<Int, Movie>(exception),
            result
        )
    }
}