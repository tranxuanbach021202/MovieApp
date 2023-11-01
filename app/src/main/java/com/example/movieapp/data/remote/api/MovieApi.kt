package com.example.movieapp.data.remote.api

import com.example.movieapp.data.remote.model.MovieModel
import com.example.movieapp.data.response.CastResponse
import com.example.movieapp.data.response.GenresResponse
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.data.response.MovieResponse
import com.example.movieapp.data.response.VideoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getMovieNowShowing(
        @Query("page") page: Int
    ) : MovieResponse

    @GET("movie/popular")
    suspend fun getMoviePopular(
        @Query("page") page: Int
    ) : MovieResponse

    @GET("genre/movie/list")
    suspend fun getGenresMovie(
    ) : GenresResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getCastsOfMovie(
        @Path("movie_id") id : Int
    ) : CastResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id : Int
    ) : MovieModel

    @GET("movie/{movie_id}/videos")
    suspend fun getMovideTrailer(
        @Path("movie_id") id : Int
    ) : VideoResponse

}