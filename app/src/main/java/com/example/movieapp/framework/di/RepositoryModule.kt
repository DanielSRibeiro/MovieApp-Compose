package com.example.movieapp.framework.di

import com.example.core.data.repository.MovieDetailsRepository
import com.example.core.data.repository.MoviePopularRepository
import com.example.core.data.repository.MovieSearchRepository
import com.example.core.data.source.MovieDetailsRemoteDataSource
import com.example.core.data.source.MoviePopularRemoteDataSource
import com.example.core.data.source.MovieSearchRemoteSource
import com.example.movieapp.framework.MovieDetailsRepositoryImpl
import com.example.movieapp.framework.MoviePopularRepositoryImpl
import com.example.movieapp.framework.MovieSearchRepositoryImpl
import com.example.movieapp.framework.data.network.response.MovieResponse
import com.example.movieapp.framework.data.network.response.SearchResponse
import com.example.movieapp.framework.source.MovieDetailsRemoteDataSourceImpl
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


    @Binds
    fun bindMovieDetailRemoteSource(
        movieDetailsRemoteDataSourceImpl: MovieDetailsRemoteDataSourceImpl
    ): MovieDetailsRemoteDataSource<MovieResponse>

    @Binds
    fun bindDetailsRepository(
        movieDetailsRepositoryImpl: MovieDetailsRepositoryImpl
    ): MovieDetailsRepository

}