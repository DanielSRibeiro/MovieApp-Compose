package com.example.movieapp.framework.paging

import androidx.paging.PagingSource
import com.example.core.data.network.source.MovieSearchRemoteSource
import com.example.core.domain.model.MovieSearch
import com.example.testing.model.MovieSearchFactory
import com.example.testing.model.MovieSearchPagingFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieSearchPagingSourceTest {

    @Mock
    lateinit var remoteDataSource: MovieSearchRemoteSource

    private val moveFactory = MovieSearchFactory()

    private val moviePagingFactory = MovieSearchPagingFactory().create()

    private val movieSearchPagingSource by lazy {
        MovieSearchPagingSource(
            query = "",
            remoteDataSource = remoteDataSource
        )
    }

    @Test
    fun `should return the correct data when load is called`() = runTest {
        whenever(remoteDataSource.getSearchMovies(any(), any()))
            .thenReturn(moviePagingFactory)

        val result = movieSearchPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        val resultExpected = listOf(
            moveFactory.create(MovieSearchFactory.Poster.Avengers),
            moveFactory.create(MovieSearchFactory.Poster.JohnWick)
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
        whenever(remoteDataSource.getSearchMovies(any(), any()))
            .thenThrow(exception)

        val result = movieSearchPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertEquals(
            PagingSource.LoadResult.Error<Int, MovieSearch>(exception),
            result
        )
    }
}