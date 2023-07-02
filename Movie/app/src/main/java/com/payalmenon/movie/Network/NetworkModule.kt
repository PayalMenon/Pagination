package com.payalmenon.movie.Network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    val BASE_URL = "https://api.themoviedb.org/3/"
    val api_key = "db26b16a5d292ea8cae570f3f3dcbb34"

    @Provides
    @Singleton
    fun providesOkHttp(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
        val interceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val url: HttpUrl = originalRequest.url().newBuilder()
                .addQueryParameter("api_key", api_key)
                .build()
            val request = originalRequest.newBuilder().url(url).build()
            chain.proceed(request)
        }
        val builder = httpBuilder.build().newBuilder().addInterceptor(interceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesNetworkService(retrofit: Retrofit) : NetworkService =
        retrofit.create(NetworkService::class.java)
}