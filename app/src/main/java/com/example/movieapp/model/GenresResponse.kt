package com.example.movieapp.model

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    val genres : List<GenresMovie>
) {


}