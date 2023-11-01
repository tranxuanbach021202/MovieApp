package com.example.movieapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.repository.MovieReponsitory
import javax.inject.Inject

class MovieNowShowingUseCase @Inject constructor(
    private val movieReponsitory: MovieReponsitory
) {

    fun invoke() : LiveData<PagingData<Movie>> = movieReponsitory.getMoviesNowShowing()
}