package com.example.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id")
    val id : String,
    @SerializedName("name")
    val name: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("site")
    val site: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("type")
    val type: String
) {
}