package com.example.movieapp.framework.di

import com.example.core.usecase.AddMovieFavoriteUseCase
import com.example.core.usecase.AddMovieFavoriteUseCaseImpl
import com.example.core.usecase.DeleteMovieFavoriteUseCase
import com.example.core.usecase.DeleteMovieFavoriteUseCaseImpl
import com.example.core.usecase.GetMovieDetailsUseCase
import com.example.core.usecase.GetMovieFavoriteUseCase
import com.example.core.usecase.GetMovieFavoriteUseCaseImpl
import com.example.core.usecase.GetPopularMoviesUseCaseImp
import com.example.core.usecase.GetMovieSearchUseCase
import com.example.core.usecase.GetMovieSearchUseCaseImp
import com.example.core.usecase.GetPopularMoviesUseCase
import com.example.core.usecase.GetPopularMoviesUseCaseImpl
import com.example.core.usecase.IsMovieFavoriteUseCase
import com.example.core.usecase.IsMovieFavoriteUseCaseImpl
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
    ): GetPopularMoviesUseCase

    @Binds
    fun bindGetMovieSearchUseCase(
        getMovieSearchUseCaseImp: GetMovieSearchUseCaseImp
    ): GetMovieSearchUseCase

    @Binds
    fun bindGetMovieDetailsUseCase(
        getPopularMoviesUseCaseImp: GetPopularMoviesUseCaseImp
    ): GetMovieDetailsUseCase

    @Binds
    fun bindAddMovieFavoriteUseCase(
        addMovieFavoriteUseCaseImpl: AddMovieFavoriteUseCaseImpl
    ): AddMovieFavoriteUseCase

    @Binds
    fun bindDeleteMovieFavoriteUseCase(
        deleteMovieFavoriteUseCaseImpl: DeleteMovieFavoriteUseCaseImpl
    ): DeleteMovieFavoriteUseCase

    @Binds
    fun bindGetMovieFavoriteUseCase(
        getMovieFavoriteUseCaseImpl: GetMovieFavoriteUseCaseImpl
    ): GetMovieFavoriteUseCase

    @Binds
    fun bindIsMovieFavoriteUseCase(
        isMovieFavoriteUseCaseImpl: IsMovieFavoriteUseCaseImpl
    ): IsMovieFavoriteUseCase
}