package com.example.movieapp.ui.moviehome

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.movieapp.data.models.GenresMovie
import com.example.movieapp.data.repositories.MovieReponsitory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MovieHomeViewModel @Inject constructor(
    private val movieReponsitory: MovieReponsitory
    ): ViewModel() {


    private val _genresLiveData = MutableLiveData<List<GenresMovie>>()
    val genresLiveData: LiveData<List<GenresMovie>> get() = _genresLiveData

    val moviesNowShowing = movieReponsitory.getMoviesNowShowing().cachedIn(viewModelScope)

    val moviesPopular = movieReponsitory.getMoviesPopular().cachedIn(viewModelScope)


    fun getGenresMovie() = viewModelScope.launch {
        try {
            val genres = movieReponsitory.getGenresMovie().genres
            _genresLiveData.postValue(genres)
        } catch (ex: Exception) {
            Log.d("GET GENRES MOVIE", ex.toString())
        }
    }
}