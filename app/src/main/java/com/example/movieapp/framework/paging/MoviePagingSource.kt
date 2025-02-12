package com.example.movieapp.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.data.source.MoviePopularRemoteDataSource
import com.example.core.domain.model.Movie
import com.example.movieapp.framework.data.network.mapper.toMovie
import com.example.movieapp.framework.data.network.response.MovieResponse
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class MoviePagingSource @Inject constructor(
    private val remoteDataSource: MoviePopularRemoteDataSource<MovieResponse>
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNumber = params.key ?: 1
            val response = remoteDataSource.getPopularMovies(page = pageNumber)
            val movies = response.results

            LoadResult.Page(
                data = movies.map { it.toMovie() },
                prevKey = if (pageNumber == 1) null else pageNumber + 1,
                nextKey = if (movies.isEmpty()) null else response.page + 1
            )
        } catch (e: IOException) {
            e.printStackTrace()
            LoadResult.Error(e)
        } catch (e: HttpException) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}