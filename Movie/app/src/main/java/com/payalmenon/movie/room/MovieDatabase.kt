package com.payalmenon.movie.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.payalmenon.movie.Model.Movie

@Database(
    entities = [
        Movie::class
    ],
    version = 1,
    exportSchema = true,
    autoMigrations = []
)

abstract class MovieDatabase : RoomDatabase(){

    abstract fun getMovieDao(): MovieDao
}

