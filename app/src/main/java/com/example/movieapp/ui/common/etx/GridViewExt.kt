package com.example.movieapp.ui.common.etx

import android.util.Log
import android.widget.BaseAdapter
import android.widget.GridView
import androidx.databinding.BindingAdapter
import com.example.movieapp.R
import com.example.movieapp.ui.moviehome.GenresMyAdapter


@BindingAdapter("genres")
fun setGenres(gridView: GridView, genres: List<Int>) {
    Log.d("Genres", genres.toString())
    val adapter = GenresMyAdapter(genres, gridView.context)
    gridView.adapter = adapter
}


@BindingAdapter("adapter")
fun setAdapter(gridView: GridView, adapter: GenresMyAdapter) {
    gridView.adapter = adapter
}