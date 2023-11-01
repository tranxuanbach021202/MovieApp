package com.example.movieapp.core.di

import com.example.movieapp.data.remote.api.ApiConfig
import com.example.movieapp.data.remote.api.MovieApi
import com.example.movieapp.data.repository.MovieReponsitoryImpl
import com.example.movieapp.domain.model.GenresMovie
import com.example.movieapp.domain.repository.MovieReponsitory
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
        return MovieReponsitoryImpl(movieApi)
    }

    @Provides
    @Singleton
    suspend fun provideGenres(movieReponsitory: MovieReponsitory) : List<GenresMovie> {
        return movieReponsitory.getGenresMovie()
    }
}