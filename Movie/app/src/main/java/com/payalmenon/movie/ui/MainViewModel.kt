package com.payalmenon.movie.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.payalmenon.movie.Model.Movie
import com.payalmenon.movie.Network.NetworkService
import com.payalmenon.movie.paging.MovieSource
import com.payalmenon.movie.repository.MovieHelper
import com.payalmenon.movie.repository.PagingMovieHelper
import com.payalmenon.movie.room.MovieDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val movieHelper: MovieHelper,
    val service: NetworkService,
    val movieDao: MovieDao
) : ViewModel() {

    val _movies: MutableSharedFlow<List<Movie>> = MutableSharedFlow()
    val movies: SharedFlow<List<Movie>> get() = _movies

    var flow: Flow<PagingData<Movie>>? = null

    fun getMovieList() {
        viewModelScope.launch {
            _movies.emit(movieHelper.getMovies())
        }
    }

    fun getMovies() {
        /*viewModelScope.launch {
            movieHelper.getMovieList().cachedIn(viewModelScope).map { data ->
                println("Payal - $data")
            }
        }*/
        flow = Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = {
                MovieSource(networkService = service, movieDao = movieDao)
            }

        ).flow
            .cachedIn(viewModelScope)

    }
}