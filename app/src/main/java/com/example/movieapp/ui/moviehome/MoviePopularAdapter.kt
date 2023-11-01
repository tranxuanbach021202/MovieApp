package com.example.movieapp.ui.moviehome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.MovieNowShowingItemBinding
import com.example.movieapp.databinding.MoviePopularItemBinding
import com.example.movieapp.domain.model.Movie
import com.example.movieapp.domain.model.MovieWithGenres

class MoviePopularAdapter(
    private val onClick: (movie: MovieWithGenres) -> Unit
) : PagingDataAdapter<MovieWithGenres, MoviePopularAdapter.MyViewHolder>(
    MOVIE_COMPARATOR
) {
    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<MovieWithGenres>() {
            override fun areItemsTheSame(
                oldItem: MovieWithGenres,
                newItem: MovieWithGenres
            ): Boolean {
                return oldItem.movie.id == newItem.movie.id
            }

            override fun areContentsTheSame(
                oldItem: MovieWithGenres,
                newItem: MovieWithGenres
            ): Boolean {
                return oldItem.movie == newItem.movie
            }

        }
    }

    inner class MyViewHolder(private val binding: MoviePopularItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val context = binding.root.context

        init {
            binding.root.setOnClickListener {
                val movieItem = getItem(absoluteAdapterPosition)
                movieItem?.let{onClick(it)}
            }
        }


        fun bind(item: MovieWithGenres) {
            with(binding) {
                itemPopular = item
            }

            val genresAdapter = GenresMyAdapter(context)
            binding.gvGenresMovie.adapter = genresAdapter
            genresAdapter.setGenres(item.genres)
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let{holder.bind(it)}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DataBindingUtil.inflate<MoviePopularItemBinding>(
            LayoutInflater.from(parent.context), R.layout.movie_popular_item,
            parent, false)
        return MyViewHolder(binding)
    }


}