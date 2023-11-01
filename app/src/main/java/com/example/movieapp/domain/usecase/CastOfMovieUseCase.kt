package com.example.movieapp.domain.usecase

import com.example.movieapp.core.Resource
import com.example.movieapp.domain.model.Cast
import com.example.movieapp.domain.repository.MovieReponsitory
import javax.inject.Inject

class CastOfMovieUseCase @Inject constructor(
    private val movieReponsitory: MovieReponsitory
) {
    suspend fun invoke(movieId : Int) : Resource<List<Cast>> = movieReponsitory.getCastsOfMovie(movieId)
}