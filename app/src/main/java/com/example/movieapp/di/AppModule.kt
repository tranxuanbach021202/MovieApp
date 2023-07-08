package com.example.movieapp.di

import com.example.movieapp.api.ApiConfig
import com.example.movieapp.api.MovieApi
import com.example.movieapp.repository.MovieReponsitory
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