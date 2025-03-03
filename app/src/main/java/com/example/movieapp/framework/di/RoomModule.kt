package com.example.movieapp.framework.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp.framework.data.local.MovieDatabase
import com.example.movieapp.framework.data.local.dao.MovieDao
import com.example.movieapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        MovieDatabase::class.java,
        Constants.MOVIE_DATABASE_NAME
    ).build()

    @Provides
    fun provideMoviesDao(database: MovieDatabase): MovieDao {
        return database.movieDao()
    }
}