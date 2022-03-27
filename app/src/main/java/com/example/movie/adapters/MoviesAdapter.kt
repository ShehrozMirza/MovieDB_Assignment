package com.example.movie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movie.R
import com.example.movie.databinding.MovieItemLayoutBinding
import com.example.movie.models.MovieModel
import com.example.movie.utils.AppConstants

class MoviesAdapter(val onPhotoSelected: (item: MovieModel, position: Int) -> Unit) :
    PagingDataAdapter<MovieModel, MoviesAdapter.MovieViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let { holder.bind(it, position) }
    }

    inner class MovieViewHolder(private val itemBinding: MovieItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: MovieModel, position: Int) {
            itemBinding.apply {

                txtTitle.text = item.title ?: "No Name"
                imgMovieBanner.load(AppConstants.MOVIE_DB_IMAGE_SMALL_SIZE_URL + item.posterPath) {
                    placeholder(R.color.color_box_background)
                    crossfade(true)
                }

                cardPhoto.setOnClickListener {
                    onPhotoSelected(item, position)
                }
            }
        }
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel) =
                oldItem == newItem
        }
    }
}
