package com.payalmenon.movie.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieRoomModule {

    @Provides
    @Singleton
    fun providesMovieDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder( context,
            MovieDatabase::class.java,
            "movie.db"
        ).build()

    @Provides
    @Singleton
    fun providesMovieDao(movieDatabase: MovieDatabase): MovieDao = movieDatabase.getMovieDao()

}