package com.example.movieapp.ui.moviedetail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemCastBinding
import com.example.movieapp.domain.model.Cast

class CastOfMovieAdapter() : RecyclerView.Adapter<CastOfMovieAdapter.MyViewHolder>() {

    private var listCast = mutableListOf<Cast>()
    fun setData(casts: List<Cast>) {
        listCast.clear()
        listCast.addAll(casts)
        Log.d("ListCastAa", listCast.toString())
        notifyDataSetChanged()
    }
    inner class MyViewHolder(private val binding: ItemCastBinding) : RecyclerView.ViewHolder(binding.root) {
        private val context = binding.root.context


        fun bind(item: Cast) {
            Log.d("ListCastA", item.toString())
            with(binding) {
                itemCast = item
            }

            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.d("ListCastAa", listCast.size.toString())
        return listCast.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cast = listCast.get(position)
        holder.bind(cast)
    }
}