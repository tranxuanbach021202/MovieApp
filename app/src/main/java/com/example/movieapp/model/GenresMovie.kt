package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

data class GenresMovie(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String
)
