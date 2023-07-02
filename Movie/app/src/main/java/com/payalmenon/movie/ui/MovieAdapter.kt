package com.payalmenon.movie.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.payalmenon.movie.Model.Movie
import com.payalmenon.movie.databinding.ListItemBinding

class MovieAdapter: PagingDataAdapter<Movie, PagingViewHolder>(MovieDiffUtil()) {
    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        holder.bindMovieToView(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        return PagingViewHolder(
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
}

class MovieDiffUtil: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

}
class PagingViewHolder(val view: ListItemBinding): RecyclerView.ViewHolder(view.root) {

    fun bindMovieToView(movie: Movie?) {
        movie?.let {
            view.itemTitle.text = movie.title
            view.itemOverview.text = movie.overview
        }
    }
}