package com.example.movieapp.domain.usecase

import androidx.lifecycle.LiveData
import com.example.movieapp.core.Resource
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieReponsitory
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val movieReponsitory: MovieReponsitory
) {
     suspend fun invoke(movieId : Int) : Resource<Movie> = movieReponsitory.getMovieDetail(movieId)
}