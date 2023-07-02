package com.payalmenon.movie.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MovieHelperModule {

    @Binds
    @Singleton
    //fun bindsMovieHelper(networkMovieHelper: NetworkMovieHelper): MovieHelper
    fun bindsPagerMovieHelper(pagingMovieHelper: PagingMovieHelper): MovieHelper
}