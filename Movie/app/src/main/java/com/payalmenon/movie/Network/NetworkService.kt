package com.payalmenon.movie.Network

import com.payalmenon.movie.Model.Movies
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkService {

    @GET("discover/movie?with_genres=18")
    suspend fun getMovies(@Query("page") page: Int): Movies

    @GET("discover/movie?with_genres=18")
    suspend fun getMovieList(): Movies
}