package com.example.movieapp.ui.common.etx

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("setVisibility")
fun View.setVisibility(bool : Boolean) {
    isVisible = bool
}