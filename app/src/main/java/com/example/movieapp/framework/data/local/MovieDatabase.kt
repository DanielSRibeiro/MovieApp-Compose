package com.example.movieapp.framework.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.framework.data.local.dao.MovieDao
import com.example.movieapp.framework.data.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDao
}