package com.example.movieapp.framework.di

import com.example.core.usecase.GetPopularMoviesUseCase
import com.example.core.usecase.GetPopularMoviesUseCaseImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetPopularUseCase(
        getPopularMoviesUseCaseImp: GetPopularMoviesUseCaseImp
    ) : GetPopularMoviesUseCase
}