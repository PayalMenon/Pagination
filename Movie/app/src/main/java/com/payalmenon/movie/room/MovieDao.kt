package com.payalmenon.movie.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.payalmenon.movie.Model.Movie
import java.io.FileDescriptor

@Dao
interface MovieDao {

    @Insert
    fun addMovie(movie: Movie)

    @Insert
    fun addAllMovies(movies: List<Movie>)

    @Query("SELECT * from movie where id= :id")
    fun getMovie(id: Int): Movie

    @Query("SELECT * from movie")
    fun getAllMovies(): List<Movie>
}