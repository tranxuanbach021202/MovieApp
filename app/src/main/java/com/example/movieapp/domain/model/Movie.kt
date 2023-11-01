package com.example.movieapp.domain.model

import androidx.room.PrimaryKey
import com.example.movieapp.data.remote.model.MovieModel
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    @PrimaryKey
    var id: Int,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("poster_path")
    val poster_path: String = "",
    @SerializedName("vote_average")
    val vote_average: Float,
    @SerializedName("genre_ids")
    val genreIds : List<Int>,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("runtime")
    val runTime: Int,
    @SerializedName("overview")
    val overview: String = ""
) {

}

fun MovieModel.toDomain() = Movie(
    id = id,
    title = title,
    poster_path = poster_path,
    vote_average = vote_average,
    genreIds = genreIds ?: emptyList(),
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    runTime = runTime,
    overview = overview
)

