package com.example.movieapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.movieapp.api.ApiConfig
import com.example.movieapp.api.MovieApi
import com.example.movieapp.data.MoviePaging
import com.example.movieapp.data.NETWORT_PAGE_SIZE
import com.example.movieapp.model.GenresResponse
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieReponsitory (private val typeMovie: String, private val movieInterface: MovieApi) {

    fun getMoviesNowShowing() : LiveData<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = NETWORT_PAGE_SIZE),
        pagingSourceFactory = {
            MoviePaging(MovieType.NOW_SHOWING.toString(), movieInterface)
        }
    ).liveData


    fun getMoviesPopular() : LiveData<PagingData<Movie>> = Pager(
        config = PagingConfig(pageSize = NETWORT_PAGE_SIZE),
        pagingSourceFactory = {
            MoviePaging(MovieType.POPULAR.toString(), movieInterface)
        }
    ).liveData


    suspend fun getGenresMovie() : GenresResponse = movieInterface.getGenresMovie()

}