package com.example.movieapp.ui.moviedetail.castofmovie

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemCastBinding
import com.example.movieapp.data.models.Cast
import com.example.movieapp.ui.common.MyViewHolder

class CastMovieAdapter(
) : RecyclerView.Adapter<MyViewHolder<ItemCastBinding>>() {

    private var _casts = emptyList<Cast>()
    fun setData(casts: List<Cast>) {
        _casts = casts
        Log.d("AdapterCast", casts.toString())
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder<ItemCastBinding> {
        val binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return _casts.size
    }

    override fun onBindViewHolder(holder: MyViewHolder<ItemCastBinding>, position: Int) {
        val cast = _casts[position]
        holder.binding.itemCast = cast
        holder.binding.executePendingBindings()
    }

}