package com.example.movieapp.framework.di

import com.example.core.data.repository.MoviePopularRepository
import com.example.core.data.repository.MovieSearchRepository
import com.example.core.data.source.MoviePopularRemoteDataSource
import com.example.core.data.source.MovieSearchRemoteSource
import com.example.movieapp.framework.MoviePopularRepositoryImpl
import com.example.movieapp.framework.MovieSearchRepositoryImpl
import com.example.movieapp.framework.data.network.response.MovieResponse
import com.example.movieapp.framework.data.network.response.SearchResponse
import com.example.movieapp.framework.source.MoviePopularRemoteDataSourceImpl
import com.example.movieapp.framework.source.MovieSearchRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindMovieDataSource(
        moviePopularRemoteDataSourceImpl: MoviePopularRemoteDataSourceImpl
    ): MoviePopularRemoteDataSource<MovieResponse>

    @Binds
    fun bindMovieRepository(
        moviePopularRepositoryImpl: MoviePopularRepositoryImpl
    ): MoviePopularRepository

    @Binds
    fun bindMovieSearchRemoteSource(
        movieSearchRemoteSourceImpl: MovieSearchRemoteSourceImpl
    ): MovieSearchRemoteSource<SearchResponse>

    @Binds
    fun bindSearchRepository(
        movieSearchRepositoryImpl: MovieSearchRepositoryImpl
    ): MovieSearchRepository
}