package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

data class CastResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cast")
    val casts: List<Cast>
) {
}