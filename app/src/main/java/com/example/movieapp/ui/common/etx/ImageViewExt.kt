package com.example.movieapp.ui.common.etx

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig


@BindingAdapter("image")
fun loadImage(view: ImageView, imageUrl: String) {
    val url = BuildConfig.BASE_URL_IMAGE + imageUrl
    Log.d("Image", url)
    Glide.with(view)
        .load(url)
        .into(view)
}