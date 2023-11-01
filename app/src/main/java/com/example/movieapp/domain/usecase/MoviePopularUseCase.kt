package com.example.movieapp.domain.usecase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagingData
import androidx.paging.map
import com.example.movieapp.domain.model.GenresMovie
import com.example.movieapp.domain.model.MovieWithGenres
import com.example.movieapp.domain.repository.MovieReponsitory
import kotlinx.coroutines.delay
import javax.inject.Inject

class MoviePopularUseCase @Inject constructor(
    private val movieReponsitory: MovieReponsitory
) {
    suspend operator fun invoke(): LiveData<PagingData<MovieWithGenres>> {
        val genres = movieReponsitory.getGenresMovie()
        val listMovie = movieReponsitory.getMoviesPopular()
        Log.d("TEST POPULAR", listMovie.toString())
        // chuyen doi genres tu ids sang string
        return Transformations.map(listMovie) { movies ->
            movies.map {movie ->
                Log.d("TEST POPULAR2", genres.toString())
                val _genres = genres.filter { genre ->
                    Log.d("TEST POPULAR3", movie.genreIds.toString())
                    movie.genreIds.contains(genre.id)
                }
                MovieWithGenres(movie, _genres)
            }
        }
    }
}