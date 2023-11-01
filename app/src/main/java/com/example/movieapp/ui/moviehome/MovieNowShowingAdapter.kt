package com.example.movieapp.ui.moviehome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.movieapp.R
import com.example.movieapp.databinding.MovieNowShowingItemBinding
import com.example.movieapp.domain.model.Movie


class MovieNowShowingAdapter(
    private val onClick: (movie: Movie) -> Unit
) : PagingDataAdapter<Movie, MovieNowShowingAdapter.MyViewHolder>(
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

    override fun onBindViewHolder(holder: MyViewHolder,position: Int) {
        getItem(position)?.let{holder.bind(it)}
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder{
        val binding = DataBindingUtil.inflate<MovieNowShowingItemBinding>(LayoutInflater.from(parent.context), R.layout.movie_now_showing_item,
            parent, false)
        return MyViewHolder(binding)
    }

    inner class MyViewHolder(private val binding: MovieNowShowingItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val context = binding.root.context

        init {
            binding.root.setOnClickListener {
                val movieItem = getItem(absoluteAdapterPosition)
                movieItem?.let{onClick(it)}
            }

        }

        fun bind(item: Movie) {
            with(binding) {
                itemMovie = item
            }
        }
    }
}