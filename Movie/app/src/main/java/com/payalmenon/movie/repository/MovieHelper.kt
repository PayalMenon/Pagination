package com.payalmenon.movie.repository

import androidx.paging.PagingData
import com.payalmenon.movie.Model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieHelper {

    suspend fun getMovies(): List<Movie>

    suspend fun getMovieList(): Flow<PagingData<Movie>>
}