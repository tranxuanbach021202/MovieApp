package com.example.movieapp.api

import com.example.movieapp.model.CastResponse
import com.example.movieapp.model.GenresResponse
import com.example.movieapp.model.MovieResponse
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
    suspend fun getCastOfMovie(
        @Path("movie_id") id : Int
    ) : CastResponse


}