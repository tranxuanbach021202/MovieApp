package com.example.movieapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.model.GenresMovie
import com.example.movieapp.model.Movie

class MoviePopularAdapter() : PagingDataAdapter<Movie, MoviePopularAdapter.MoviePopularViewHolder>(
    MOVIE_COMPARATOR
) {


    inner class MoviePopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtMovieName : TextView = itemView.findViewById(R.id.txt_movie_popular_name)
        private val txtVote : TextView = itemView.findViewById(R.id.txt_vote_average_popular)
        private val ivPorterMovie : ImageView = itemView.findViewById(R.id.iv_movie_popular_porter)
        private val gridView : GridView = itemView.findViewById(R.id.gv_genres_movie)
        fun onBind(movie: Movie?) {
            txtMovieName.text = movie?.title
            txtVote.text = movie?.vote_average.toString()

            val imgUrl = BASE_URL_IMAGE + "w92" + movie?.poster_path.toString()
//            val genres = arrayListOf("bach", "bach", "tran", "bach")
//            gridView.adapter = GenresMyAdapter(genres, itemView.context)

            setGenreMovieItem(movie!!.genreIds)

        }

        fun setGenreMovieItem(genreIds : List<Int>) {
            gridView.adapter =  GenresMyAdapter(genreIds, itemView.context)
        }
    }






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePopularViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_popular_item, parent, false)
        return MoviePopularViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MoviePopularViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }


            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}