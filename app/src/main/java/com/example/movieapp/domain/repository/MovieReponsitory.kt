package com.example.movieapp.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.movieapp.core.Resource
import com.example.movieapp.domain.model.Cast
import com.example.movieapp.domain.model.GenresMovie
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.Video

interface MovieReponsitory {

    fun getMoviesNowShowing() : LiveData<PagingData<Movie>>

    fun getMoviesPopular() : LiveData<PagingData<Movie>>

    suspend fun getGenresMovie(): List<GenresMovie>

    suspend fun getCastsOfMovie(movieId: Int): Resource<List<Cast>>

    suspend fun getMovieDetail(movieId: Int): Resource<Movie>

    suspend fun getMovieVideo(movieId: Int): Resource<List<Video>>



}