package com.example.movieapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.model.GenresMovie
import com.example.movieapp.repository.MovieReponsitory
import com.example.movieapp.model.Movie
import com.example.movieapp.ultis.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
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