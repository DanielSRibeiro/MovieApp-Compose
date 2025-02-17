package com.example.movieapp.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.data.source.MovieSearchRemoteSource
import com.example.core.domain.model.MovieSearch
import com.example.movieapp.framework.data.network.mapper.toMovieSearch
import com.example.movieapp.framework.data.network.response.SearchResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieSearchPagingSource @Inject constructor(
    private val query: String,
    private val remoteDataSource: MovieSearchRemoteSource<SearchResponse>
): PagingSource<Int, MovieSearch>() {

    override fun getRefreshKey(state: PagingState<Int, MovieSearch>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(MoviePagingSource.LIMIT) ?: anchorPage?.nextKey?.minus(
                MoviePagingSource.LIMIT
            )
        }    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSearch> {
        return try {
            val pageNumber = params.key ?: 1
            val response = remoteDataSource.getSearchMovies(page = pageNumber, query = query)
            val movies = response.results

            LoadResult.Page(
                data = movies.map { it.toMovieSearch() },
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (movies.isEmpty()) null else response.page + 1
            )
        } catch (e: IOException) {
            e.printStackTrace()
            LoadResult.Error(e)
        } catch (e: HttpException) {
            e.printStackTrace()
            LoadResult.Error(e)
        }    }

    companion object {
        private const val LIMIT = 20
    }
}