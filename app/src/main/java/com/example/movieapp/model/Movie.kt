package com.example.movieapp.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    @PrimaryKey
    var id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("vote_average")
    val vote_average: Float,
    @SerializedName("genre_ids")
    val genreIds : List<Int>
) {

}