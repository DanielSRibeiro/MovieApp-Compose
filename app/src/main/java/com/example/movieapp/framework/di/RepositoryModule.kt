package com.example.movieapp.framework.di

import com.example.core.data.local.repository.MovieFavoriteRepository
import com.example.core.data.network.repository.MovieDetailsRepository
import com.example.core.data.network.repository.MoviePopularRepository
import com.example.core.data.network.repository.MovieSearchRepository
import com.example.movieapp.framework.repository.MovieDetailsRepositoryImpl
import com.example.movieapp.framework.repository.MoviePopularRepositoryImpl
import com.example.movieapp.framework.repository.MovieSearchRepositoryImpl
import com.example.movieapp.framework.repository.MovieFavoriteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindMovieRepository(
        moviePopularRepositoryImpl: MoviePopularRepositoryImpl
    ): MoviePopularRepository

    @Binds
    fun bindSearchRepository(
        movieSearchRepositoryImpl: MovieSearchRepositoryImpl
    ): MovieSearchRepository

    @Binds
    fun bindDetailsRepository(
        movieDetailsRepositoryImpl: MovieDetailsRepositoryImpl
    ): MovieDetailsRepository

    @Binds
    fun bindMovieFavoriteRepository(
        movieFavoriteRepositoryImpl: MovieFavoriteRepositoryImpl
    ): MovieFavoriteRepository

}