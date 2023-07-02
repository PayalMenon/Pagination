package com.payalmenon.movie.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.payalmenon.movie.Model.Movie
import com.payalmenon.movie.Network.NetworkService
import com.payalmenon.movie.paging.MovieSource
import com.payalmenon.movie.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PagingMovieHelper @Inject constructor(
    private val networkService: NetworkService,
    val movieDao: MovieDao
): MovieHelper {

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }

    override suspend fun getMovies(): List<Movie> {
        TODO("Not need to implemented")
    }

    override suspend fun getMovieList(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                initialLoadSize = 20,
            ),
            pagingSourceFactory = {
                MovieSource(networkService = networkService, movieDao = movieDao)
            }

        ).flow
    }
}