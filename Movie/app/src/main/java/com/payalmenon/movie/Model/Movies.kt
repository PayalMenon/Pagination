package com.payalmenon.movie.Model

data class Movies(
    val pageNo: Int,
    val totalPages: Int,
    val results: List<Movie>
)