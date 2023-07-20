package com.example.movieapp.ui.moviehome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.MoviePopularItemBinding
import com.example.movieapp.ui.common.MyViewHolder

class GenreMyAdapter(private val data: List<Int>) :
    RecyclerView.Adapter<MyViewHolder<MoviePopularItemBinding>>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder<MoviePopularItemBinding> {
        val binding = MoviePopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder<MoviePopularItemBinding>, position: Int) {
        val genre = data[position]
        holder.binding.apply {
            executePendingBindings()
        }

    }
}