package com.example.movieapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val AUTHORIZATION = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1ZDhjMDQyNGJhYTI5OGM0MWVkM2I3ZGZlNmZjM2VkNyIsInN1YiI6IjY0OTJkZjIzNGJhNTIyMDBmZjAxNThhOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.O0ATr6c3PMqI6fjeOpNi8A9fFrLhGOfJsHiG4Ny5jQg"
private const val API_KEY = "5d8c0424baa298c41ed3b7dfe6fc3ed7"
object ApiConfig {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p"


    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .header("Authorization", AUTHORIZATION)
                .build()
            chain.proceed(request)
        }
        .build()


        private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)

    val retrofit = builder.build()

    // Tạo api service từ Retrofit
    val createMovieApi  : MovieApi = retrofit.create(MovieApi::class.java)

}
