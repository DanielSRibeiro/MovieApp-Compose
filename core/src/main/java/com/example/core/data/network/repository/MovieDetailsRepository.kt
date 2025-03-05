package com.example.core.data.network.repository

import androidx.paging.PagingSource
import com.example.core.domain.model.Movie
import com.example.core.domain.model.MovieDetail

interface MovieDetailsRepository {
    suspend fun getMoviesDetails(movieId: Int): MovieDetail

    fun getMoviesSimilar(movieId: Int): PagingSource<Int, Movie>
}