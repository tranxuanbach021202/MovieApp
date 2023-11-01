package com.example.movieapp.data.response

import com.example.movieapp.domain.model.Video
import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("id")
    val id : Int,
    @SerializedName("results")
    val results : List<Video>
)
