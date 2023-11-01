package com.example.movieapp.data.response

import com.example.movieapp.domain.model.GenresMovie
import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    val genres : List<GenresMovie>
) {


}