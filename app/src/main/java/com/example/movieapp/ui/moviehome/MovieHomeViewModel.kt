package com.example.movieapp.ui.moviehome

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieapp.domain.model.GenresMovie
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.data.repository.MovieReponsitoryImpl
import com.example.movieapp.domain.model.MovieWithGenres
import com.example.movieapp.domain.usecase.MovieNowShowingUseCase
import com.example.movieapp.domain.usecase.MoviePopularUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MovieHomeViewModel @Inject constructor(
    private  val movieNowShowingUseCase: MovieNowShowingUseCase,
    private val moviePopularUseCase: MoviePopularUseCase
    ): ViewModel() {

    private val _listMovieNowShowing = MutableLiveData<PagingData<Movie>>()
    suspend fun getMovieNowShowing() : LiveData<PagingData<Movie>> {
        val response = movieNowShowingUseCase.invoke().cachedIn(viewModelScope)
        _listMovieNowShowing.value = response.value
        return response
    }

    private val _listMoviePoular = MutableLiveData<PagingData<MovieWithGenres>>()
    suspend fun getMoviePopular(): LiveData<PagingData<MovieWithGenres>> {
        val response = moviePopularUseCase.invoke().cachedIn(viewModelScope)
        _listMoviePoular.value = response.value
        return response
    }

}