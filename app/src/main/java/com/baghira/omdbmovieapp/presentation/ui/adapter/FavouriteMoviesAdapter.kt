package com.baghira.omdbmovieapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.baghira.omdbmovieapp.R
import com.baghira.omdbmovieapp.data.model.MovieResponse

class FavouriteMoviesAdapter :
    RecyclerView.Adapter<MovieViewHolder>() {
    private var favouriteList = ArrayList<MovieResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItems(favouriteList[position])
    }

    override fun getItemCount(): Int {
        return favouriteList.size
    }

}
