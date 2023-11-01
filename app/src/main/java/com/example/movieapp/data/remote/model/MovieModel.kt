package com.example.movieapp.data.remote.model

import androidx.room.PrimaryKey
import com.example.movieapp.domain.model.GenresMovie
import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("id")
    @PrimaryKey
    var id: Int,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("poster_path")
    var poster_path: String = "",
    @SerializedName("vote_average")
    var vote_average: Float,
    var genreIds : List<Int>,
    @SerializedName("backdrop_path")
    var backdropPath: String = "",
    @SerializedName("original_language")
    var originalLanguage: String = "",
    @SerializedName("original_title")
    var originalTitle: String = "",
    @SerializedName("runtime")
    var runTime: Int,
    @SerializedName("overview")
    var overview: String = ""
)
