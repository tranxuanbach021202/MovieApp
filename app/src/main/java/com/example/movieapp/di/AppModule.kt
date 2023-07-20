package com.example.movieapp.di

import com.example.movieapp.data.apis.ApiConfig
import com.example.movieapp.data.apis.MovieApi
import com.example.movieapp.data.repositories.MovieReponsitory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMovieApi() : MovieApi = ApiConfig.createMovieApi

    @Provides
    fun provideRepository(movieApi: MovieApi) : MovieReponsitory {
        return MovieReponsitory("", movieApi)
    }
}