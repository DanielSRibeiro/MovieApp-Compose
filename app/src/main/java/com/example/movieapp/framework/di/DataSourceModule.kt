package com.example.movieapp.framework.di

import com.example.core.data.local.source.MovieFavoriteLocalDataSource
import com.example.core.data.network.source.MovieDetailsRemoteDataSource
import com.example.core.data.network.source.MoviePopularRemoteDataSource
import com.example.core.data.network.source.MovieSearchRemoteSource
import com.example.movieapp.framework.data.network.response.MovieResponse
import com.example.movieapp.framework.data.network.response.SearchResponse
import com.example.movieapp.framework.source.MovieDetailsRemoteDataSourceImpl
import com.example.movieapp.framework.source.MovieFavoriteLocalDataSourceImpl
import com.example.movieapp.framework.source.MoviePopularRemoteDataSourceImpl
import com.example.movieapp.framework.source.MovieSearchRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindMovieDataSource(
        moviePopularRemoteDataSourceImpl: MoviePopularRemoteDataSourceImpl
    ): MoviePopularRemoteDataSource<MovieResponse>

    @Binds
    fun bindMovieSearchRemoteSource(
        movieSearchRemoteSourceImpl: MovieSearchRemoteSourceImpl
    ): MovieSearchRemoteSource<SearchResponse>

    @Binds
    fun bindMovieDetailRemoteSource(
        movieDetailsRemoteDataSourceImpl: MovieDetailsRemoteDataSourceImpl
    ): MovieDetailsRemoteDataSource<MovieResponse>

    @Binds
    fun bindMovieFavoriteLocalDataSource(
        movieFavoriteLocalDataSourceImpl: MovieFavoriteLocalDataSourceImpl
    ): MovieFavoriteLocalDataSource

}