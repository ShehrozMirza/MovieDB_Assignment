package com.example.movie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.databinding.LoadStateFooterViewItemBinding
import com.example.movie.utils.AppConstants
import java.lang.StringBuilder

class LoadStateViewHolder(private val binding: LoadStateFooterViewItemBinding, retry: () -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text =
                StringBuilder().append(AppConstants.ANGRY)
                    .append(itemView.context.getString(R.string.error_text_label)).toString()
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): LoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.load_state_footer_view_item, parent, false)
            val binding = LoadStateFooterViewItemBinding.bind(view)
            return LoadStateViewHolder(binding, retry)
        }
    }

}