package com.example.movieapp.domain.model

data class MovieWithGenres(
    val movie: Movie,
    val genres: List<GenresMovie>
)
