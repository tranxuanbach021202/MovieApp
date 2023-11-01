package com.example.movieapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.movieapp.data.remote.api.MovieApi
import com.example.movieapp.core.MoviePaging
import com.example.movieapp.core.NETWORT_PAGE_SIZE
import com.example.movieapp.core.Resource
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.MovieType
import com.example.movieapp.domain.model.Cast
import com.example.movieapp.domain.model.GenresMovie
import com.example.movieapp.domain.model.Video
import com.example.movieapp.domain.model.toDomain
import com.example.movieapp.domain.repository.MovieReponsitory
import java.lang.Exception


class MovieReponsitoryImpl (private val movieApi: MovieApi)  : MovieReponsitory{

    override  fun getMoviesNowShowing() : LiveData<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = NETWORT_PAGE_SIZE),
        pagingSourceFactory = {
            MoviePaging(MovieType.NOW_SHOWING.toString(), movieApi)
        }
    ).liveData




    override fun getMoviesPopular() : LiveData<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = NETWORT_PAGE_SIZE),
        pagingSourceFactory = {
            MoviePaging(MovieType.POPULAR.toString(), movieApi)
        }
    ).liveData


    override suspend fun getGenresMovie() : List<GenresMovie> {
        Log.d("GenresRepo", "call")
        return movieApi.getGenresMovie().genres
    }



    override suspend fun getCastsOfMovie(movieId : Int) : Resource<List<Cast>> {
        try {
            val casts = movieApi.getCastsOfMovie(movieId).casts
            return Resource.Success(casts)
        } catch (ex : Exception) {
            return Resource.Error(ex)
        }
    }

    override suspend fun getMovieDetail(movieId: Int) : Resource<Movie> {
        try {
            Log.d("MovieDetailc", "a")
            val movie = movieApi.getMovieDetail(movieId).toDomain()
            return Resource.Success(movie)
        } catch (ex: Exception) {
            return Resource.Error(ex)
        }
    }


    override suspend fun getMovieVideo(movieId: Int) : Resource<List<Video>>{
        try {
            val listVideo = movieApi.getMovideTrailer(movieId).results
            return Resource.Success(listVideo)
        } catch (ex: Exception) {
            return Resource.Error(ex)
        }
    }
}