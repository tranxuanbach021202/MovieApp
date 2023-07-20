package com.example.movieapp.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.apis.MovieApi
import com.example.movieapp.data.models.Movie
import com.example.movieapp.data.models.MovieResponse
import com.example.movieapp.data.models.MovieType


private const val MOVIE_STARTING_PAGE_INDEX = 1
const val NETWORT_PAGE_SIZE = 40


class MoviePaging(val typeMovie: String,  val movieInterface: MovieApi) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: MOVIE_STARTING_PAGE_INDEX
        return try {
            val response: MovieResponse = when(typeMovie) {
                MovieType.NOW_SHOWING.toString() -> movieInterface.getMovieNowShowing(page)

                else -> {
                    movieInterface.getMoviePopular(page)
                }
            }
            val movies = response?.results

            Log.d("Datar${typeMovie}", movies.toString())
            LoadResult.Page(
                data = movies!!,
                prevKey = if (page == MOVIE_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (page == response.totalPages) null else page + 1
            )
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }
}