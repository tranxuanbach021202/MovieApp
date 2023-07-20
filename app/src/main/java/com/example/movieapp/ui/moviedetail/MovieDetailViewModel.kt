package com.example.movieapp.ui.moviedetail

import android.provider.MediaStore.Video
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.repositories.MovieReponsitory
import com.example.movieapp.data.repositories.RepoResult
import com.example.movieapp.data.repositories.data
import com.example.movieapp.data.repositories.error
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieReponsitory: MovieReponsitory
) : ViewModel() {

    private val _movieId = MutableLiveData<Int>()

    var isLoading = MutableLiveData(false)
    fun setMovieId(movieId: Int) {
        Log.d("MovieDetails", movieId.toString())
        _movieId.value = movieId
    }


    fun getMovieId() : Int? = _movieId.value


    val castResponse = _movieId.switchMap { movieReponsitory.getCastsOfMovie(it)}

    val casts = castResponse.map { it.data?.casts }

    val loading = castResponse.map { it is RepoResult.Loading }
    val error = castResponse.map { it.error }


    fun getCastsOfMovie(movieId : Int) = viewModelScope.launch {
        try {
            val casts = movieReponsitory.getCastsOfMovie(movieId)
        } catch (ex : Exception) {
            Log.d("MovieDetailViewModel", ex.toString())
        }
    }

    val movieResponse = _movieId.switchMap { movieReponsitory.getMovieDetail(it) }

    val movieDetail = movieResponse.map { it.data }

    val loading_movie = movieResponse.map { it is RepoResult.Loading }

    val error_movie = movieResponse.map { it.error }


    val videoResponse = _movieId.switchMap { movieReponsitory.getMovideVideo(it) }


    val videoDetail  = videoResponse.map { it.data?.results }



    fun getKeyVideo(): String? {
        val video = videoDetail.value
        var trailerKey: String? = null
        val trailerVideo = video!!.find { it.type == "Trailer" }
        if (trailerVideo != null) {
            trailerKey = trailerVideo!!.key
        }
        return trailerKey
    }
}