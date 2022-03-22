package com.example.androidnewarchitecture.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.androidnewarchitecture.R
import com.example.androidnewarchitecture.databinding.MovieItemLayoutBinding
import com.example.androidnewarchitecture.models.MovieModel

class MoviesAdapter(val onPhotoSelected: (item: MovieModel, position: Int) -> Unit) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private val movieItems: ArrayList<MovieModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieItems[position], position)
    }

    override fun getItemCount() = movieItems.size

    fun updateItems(photosList: List<MovieModel>) {
        movieItems.clear()
        movieItems.addAll(photosList)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val itemBinding: MovieItemLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: MovieModel, position: Int) {
            itemBinding.apply {

                imgPhoto.load("https://image.tmdb.org/t/p/w185/${item.posterPath}") {
                    placeholder(R.color.color_box_background)
                    crossfade(true)
                }

                cardPhoto.setOnClickListener {
                    onPhotoSelected(item, position)
                }
            }
        }
    }
}
