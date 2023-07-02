package com.payalmenon.movie.paging

import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.payalmenon.movie.Model.Movie
import com.payalmenon.movie.Network.NetworkService
import com.payalmenon.movie.room.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieSource constructor(
    val networkService: NetworkService,
    val movieDao: MovieDao
): PagingSource<Int, Movie>() {

    companion object {
        const val START_PAGE_INDEX = 1
        const val NETWORK_PAGE_SIZE = 20
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val pageIndex = params.key ?: START_PAGE_INDEX
        val response = networkService.getMovies(pageIndex)
        return withContext(Dispatchers.IO) {
            movieDao.addAllMovies(response.results)
            val movies = movieDao.getAllMovies()
            val nextPage = if(movies.isEmpty()) null else {
                pageIndex + (params.loadSize/NETWORK_PAGE_SIZE)
            }
             LoadResult.Page(
                prevKey = if(pageIndex == START_PAGE_INDEX) null else pageIndex,
                nextKey = nextPage,
                data = movies
            )
        }

    }
}