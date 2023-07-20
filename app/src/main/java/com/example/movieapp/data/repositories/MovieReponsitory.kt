package com.example.movieapp.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.movieapp.data.apis.MovieApi
import com.example.movieapp.data.MoviePaging
import com.example.movieapp.data.NETWORT_PAGE_SIZE
import com.example.movieapp.data.models.CastResponse
import com.example.movieapp.data.models.GenresResponse
import com.example.movieapp.data.models.Movie
import com.example.movieapp.data.models.MovieType
import com.example.movieapp.data.models.VideoResponse
import java.lang.Exception


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


    fun getCastsOfMovie(movieId : Int) : LiveData<RepoResult<CastResponse>> = liveData {
        try {
            emit(RepoResult.Loading)
            val castResponse = movieInterface.getCastsOfMovie(movieId)
            emit(RepoResult.Success(castResponse))
        } catch (ex : Exception) {
            emit(RepoResult.Error(ex))
        }
    }

    fun getMovieDetail(movieId: Int) : LiveData<RepoResult<Movie>> = liveData {
        try {
            Log.d("MovieDetailc", "a")
            emit(RepoResult.Loading)
            val movie = movieInterface.getMovieDetail(movieId)
            emit(RepoResult.Success(movie))
        } catch (ex: Exception) {
            emit(RepoResult.Error(ex))
        }
    }

    fun getMovideVideo(movieId: Int) : LiveData<RepoResult<VideoResponse>> = liveData {
        try {
            emit(RepoResult.Loading)
            val video = movieInterface.getMovideTrailer(movieId)
            emit(RepoResult.Success(video))
        } catch (ex: Exception) {
            emit(RepoResult.Error(ex))
        }
    }
}