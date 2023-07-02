package com.payalmenon.movie.repository

import androidx.paging.PagingData
import com.payalmenon.movie.Model.Movie
import com.payalmenon.movie.Network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkMovieHelper @Inject constructor(
    private val networkService: NetworkService
) : MovieHelper {

    override suspend fun getMovies(): List<Movie> {
        return withContext(Dispatchers.IO) {
            val movies = networkService.getMovieList()
            movies.results
        }
    }

    override suspend fun getMovieList(): Flow<PagingData<Movie>> {
        TODO("No need to implemented")
    }
}