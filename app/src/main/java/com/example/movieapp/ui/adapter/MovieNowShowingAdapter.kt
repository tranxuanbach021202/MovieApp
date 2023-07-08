package com.example.movieapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.model.GenresMovie
import com.example.movieapp.model.Movie

const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/"
class MovieNowShowingAdapter(
) : PagingDataAdapter<Movie, MovieNowShowingAdapter.MovieNowShowingViewHoldel>(
    MOVIE_COMPARATOR
) {

    inner class MovieNowShowingViewHoldel(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val txtNameMovie : TextView = itemView.findViewById(R.id.txt_movie_name)
        private val imgPorter : ImageView = itemView.findViewById(R.id.img_poster)
        private val txtVoteAverage : TextView = itemView.findViewById(R.id.txt_vote_average)
        fun onBind(movie: Movie?) {
            Log.d("DataSource", movie?.title.toString())
            txtNameMovie.text = movie?.title
            txtVoteAverage.text = movie?.vote_average.toString()


            val imgUrl = BASE_URL_IMAGE + "w154"+ movie?.poster_path.toString()
//            Glide.with(context)
//                .load(imgUrl)
//                .error(R.drawable.image1)
//                .into(imgPorter)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieNowShowingViewHoldel {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_now_showing_item, parent, false)
        return MovieNowShowingViewHoldel(itemView)
    }

    override fun onBindViewHolder(holder: MovieNowShowingViewHoldel, position: Int) {
        Log.d("Adapter", position.toString())
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