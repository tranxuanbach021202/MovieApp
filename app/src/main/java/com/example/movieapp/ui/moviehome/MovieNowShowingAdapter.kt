package com.example.movieapp.ui.moviehome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.movieapp.databinding.MovieNowShowingItemBinding
import com.example.movieapp.data.models.Movie
import com.example.movieapp.ui.common.MyViewHolder

class MovieNowShowingAdapter(
    private val onClick: (Movie) -> Unit
) : PagingDataAdapter<Movie, MyViewHolder<MovieNowShowingItemBinding>>(
    MOVIE_COMPARATOR
) {

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

    override fun onBindViewHolder(holder: MyViewHolder<MovieNowShowingItemBinding>, position: Int) {
        val movie = getItem(position)
        holder.itemView.setOnClickListener {
            onClick(movie!!)
        }
        holder.binding.apply {
            item = movie
            executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder<MovieNowShowingItemBinding> {
        val binding = MovieNowShowingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
}