package com.example.movieapp.data.remote.api

import com.example.movieapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object ApiConfig {

    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .header("Authorization", BuildConfig.AUTHORIZATION)
                .build()
            chain.proceed(request)
        }
        .build()


        private val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)

    val retrofit = builder.build()

    // Tạo api service từ Retrofit
    val createMovieApi  : MovieApi = retrofit.create(MovieApi::class.java)

}
