package com.example.movieapp.ui.moviedetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.movieapp.core.Resource
import com.example.movieapp.data.repository.MovieReponsitoryImpl
import com.example.movieapp.domain.model.Cast
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.usecase.CastOfMovieUseCase
import com.example.movieapp.domain.usecase.KeyVideoUseCase
import com.example.movieapp.domain.usecase.MovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase,
    private val castOfMovieUseCase: CastOfMovieUseCase,
    private val keyVideoUseCase: KeyVideoUseCase
) : ViewModel() {

    private val _movieDetail = MutableLiveData<Movie>()
    val movieDetal : LiveData<Movie> get() = _movieDetail
    fun fetchMovieDetail(movieId: Int) {
        viewModelScope.launch {
            val reponse =  movieDetailUseCase.invoke(movieId)
            when(reponse) {
                is Resource.Success -> {
                    Log.d("MovieViewModelF", reponse.data.runTime.toString())
                    _movieDetail.value = reponse.data
                }
                is Resource.Error -> {
                    Log.d("MovieViewModelF", "errro")
                    throw reponse.exception
                }
            }
        }
    }

    private val _listCast = MutableLiveData<List<Cast>>()
    val listCast : LiveData<List<Cast>> get() = _listCast

    fun getCastOfMovie(movieId: Int) {
        viewModelScope.launch {

                val response = castOfMovieUseCase.invoke(movieId)
                when(response) {
                    is Resource.Success -> {
                        _listCast.value = response.data
                    }
                    is Resource.Error -> {
                        response.exception
                    }
                }
        }
    }


    private val _keyVideo = MutableLiveData<String>()
    val keyVideo : LiveData<String> get() = _keyVideo
    fun getKeyVideoTrailer(movieId: Int) {
        viewModelScope.launch {
            val response = keyVideoUseCase.invoke(movieId)
            when(response) {
                is Resource.Success ->{
                    _keyVideo.value = response.data
                }

                is Resource.Error -> {
                    Log.d("Keyvideo", response.exception.toString())
                }
            }
        }
    }



}