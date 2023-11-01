package com.example.movieapp.ui.moviehome

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.movieapp.R
import com.example.movieapp.domain.model.GenresMovie

class GenresMyAdapter(
    private val context: Context
) : BaseAdapter(){

    // for layout inflater, course image view and course text view.
    private var layoutInflater: LayoutInflater? = null


    private lateinit var txtGenres : TextView

    private val genresList: MutableList<GenresMovie> = mutableListOf()

    fun setGenres(genres: List<GenresMovie>) {
        genresList.clear()
        genresList.addAll(genres)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return genresList.size
    }

    override fun getItem(position: Int): Any ?{
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        // on blow line we are checking if layout inflater
        // is null, if it is null we are initializing it.
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        // on the below line we are checking if convert view is null.
        // If it is null we are initializing it.
        if (convertView == null) {
            // on below line we are passing the layout file
            // which we have to inflate for each item of grid view.
            convertView = layoutInflater!!.inflate(R.layout.grid_view_genre_item, null)
        }

        txtGenres = convertView!!.findViewById(R.id.txtGenres)

        txtGenres.text = genresList.get(position).name.toString()

        return convertView
    }

}