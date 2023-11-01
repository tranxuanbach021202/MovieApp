package com.example.movieapp.domain.usecase

import com.example.movieapp.core.Resource
import com.example.movieapp.domain.model.Video
import com.example.movieapp.domain.repository.MovieReponsitory
import kotlinx.coroutines.withContext
import javax.inject.Inject

class KeyVideoUseCase @Inject constructor(
    private val movieReponsitory: MovieReponsitory
) {

    suspend fun invoke(movieId: Int) : Resource<String> {
        val reponse = movieReponsitory.getMovieVideo(movieId)
        when(reponse) {
            is Resource.Success -> {
                try {
                    return Resource.Success(getKeyVideoTrailerYoutube(reponse.data))
                } catch (ex: Exception) {
                   return Resource.Error(ex)
                }
            }

            is Resource.Error -> {
                throw reponse.exception
            }
        }
    }

    private fun getKeyVideoTrailerYoutube(videos: List<Video>) : String {
        val video = videos.first{
            it.type == "Trailer" && it.site == "YouTube"
        }
        return video.key
    }
}