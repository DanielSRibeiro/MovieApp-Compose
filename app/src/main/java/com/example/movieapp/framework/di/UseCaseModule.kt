package com.example.movieapp.framework.di

import com.example.core.usecase.GetMovieDetailsUseCase
import com.example.core.usecase.GetPopularMoviesUseCaseImp
import com.example.core.usecase.GetMovieSearchUseCase
import com.example.core.usecase.GetMovieSearchUseCaseImp
import com.example.core.usecase.GetPopularMoviesUseCase
import com.example.core.usecase.GetPopularMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetPopularUseCase(
        getPopularMoviesUseCaseImpl: GetPopularMoviesUseCaseImpl
    ) : GetPopularMoviesUseCase

    @Binds
    fun bindGetMovieSearchUseCase(
        getMovieSearchUseCaseImp: GetMovieSearchUseCaseImp
    ) : GetMovieSearchUseCase

    @Binds
    fun bindGetMovieDetailsUseCase(
        getPopularMoviesUseCaseImp: GetPopularMoviesUseCaseImp
    ) : GetMovieDetailsUseCase
}