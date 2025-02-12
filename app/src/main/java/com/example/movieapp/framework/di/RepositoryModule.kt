package com.example.movieapp.framework.di

import com.example.core.data.repository.MoviePopularRepository
import com.example.core.data.source.MoviePopularRemoteDataSource
import com.example.movieapp.framework.MoviePopularRepositoryImpl
import com.example.movieapp.framework.data.network.response.MovieResponse
import com.example.movieapp.framework.source.MoviePopularRemoteDataSourceImpl
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
}