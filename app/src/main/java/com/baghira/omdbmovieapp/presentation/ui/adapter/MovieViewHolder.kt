package com.baghira.omdbmovieapp.presentation.ui.adapter

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baghira.omdbmovieapp.R
import com.baghira.omdbmovieapp.data.model.MovieResponse
import com.baghira.omdbmovieapp.presentation.ui.activity.MovieDetailActivity

class MovieViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val favouriteImage: ImageView = itemView.findViewById(R.id.favorite)
        var count = true

        fun bindItems(movie: MovieResponse?) {
            favouriteImage.setImageResource(R.drawable.star_hollow)
            favouriteImage.setOnClickListener {
                if (!count) {
                    count = true
                    favouriteImage.setImageResource(R.drawable.star_hollow)
                } else {
                    count = false
                    favouriteImage.setImageResource(R.drawable.star_filled)
                }
            }
            itemView.setOnClickListener {
                movie.let {
                    val intent =
                        Intent(
                            itemView.context,
                            MovieDetailActivity::class.java
                        ).apply {
                            putExtra("sandeep", position)
                        }
                    itemView.context.startActivity(intent)
                }

            }

    }

}
