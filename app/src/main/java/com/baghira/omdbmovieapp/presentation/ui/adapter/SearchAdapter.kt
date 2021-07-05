package com.baghira.omdbmovieapp.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.baghira.omdbmovieapp.R
import com.baghira.omdbmovieapp.common.SearchItem
import com.baghira.omdbmovieapp.databinding.ListItemMovieBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class SearchAdapter : PagedListAdapter<SearchItem, SearchViewHolder>(searchDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return SearchViewHolder(ListItemMovieBinding.bind(itemView))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.apply {
            itemPos = position
            getItem(position)?.apply {
                bind(this)
            }
        }
    }

}

class SearchViewHolder(private val viewBinding: ListItemMovieBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {
    var itemPos: Int = -1
    fun bind(item: SearchItem) {
        viewBinding.apply {
            Glide.with(moviePoster.context).load(item.Poster)
                .centerCrop()
                .thumbnail(0.5f)
                .placeholder(R.drawable.ic_launcher_background)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(moviePoster)
            movieTitle.text = item.Title
            textYear.text = item.Year
        }
    }
}

val searchDiffUtil = object : DiffUtil.ItemCallback<SearchItem>() {
    override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem) =
        oldItem.imdbID == newItem.imdbID

    override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem) =
        oldItem == newItem
}