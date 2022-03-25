package com.example.movie.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.movie.R
import com.example.movie.adapters.CustomLoadStateAdapter
import com.example.movie.adapters.MoviesAdapter
import com.example.movie.base.BaseFragment
import com.example.movie.databinding.TrendingMoviesFragmentBinding
import com.example.movie.utils.AppConstants.ANGRY
import com.example.movie.utils.ErrorUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

@AndroidEntryPoint
class TrendingMoviesFragment : BaseFragment<TrendingMoviesFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> TrendingMoviesFragmentBinding
        get() = TrendingMoviesFragmentBinding::inflate

    private val viewModel: TrendingMoviesViewModel by viewModels()

    private lateinit var moviesAdapter: MoviesAdapter
    private var job: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupObservers()
        setupRecyclerView()
    }

    private fun setupObservers() {
        viewModel.trendingMovies.observe(viewLifecycleOwner) {
            moviesAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun setupRecyclerView() {
        // Movies RecyclerView
        moviesAdapter = MoviesAdapter { item, _ ->
            findNavController().navigate(
                TrendingMoviesFragmentDirections.actionTrendingMoviesFragmentToMovieDetailFragment(
                    item
                )
            )
        }
        bi.recyclerTrendingMovies.adapter = moviesAdapter

        bi.recyclerTrendingMovies.adapter = moviesAdapter.withLoadStateFooter(
            footer = CustomLoadStateAdapter {
                moviesAdapter.retry()
            }
        )

        viewLifecycleOwner.lifecycleScope.launch {
            moviesAdapter.loadStateFlow.collect { loadState ->
                val refreshState = loadState.refresh

                //Only show the list if refresh succeeds.
                bi.recyclerTrendingMovies.isVisible = refreshState is LoadState.NotLoading
                bi.progressBar.isVisible = refreshState is LoadState.Loading
                bi.layoutError.isVisible = refreshState is LoadState.Error
                bi.reloadPostsBtn.isVisible = refreshState is LoadState.Error
                bi.labelError.isVisible = refreshState is LoadState.Error

                if (refreshState is LoadState.Error) {
                    when (refreshState.error as Exception) {
                        is HttpException -> {
                            val httpException = refreshState.error as HttpException
                            httpException.response()?.let {
                                val error = ErrorUtils.parseError(it)
                                bi.labelError.text = error.statusMessage
                            }
                        }
                        is IOException -> {
                            bi.labelError.text =
                                getString(R.string.message_no_network_connected_str)
                        }
                    }
                }

                val errorState = loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                errorState?.let {
                    Toast.makeText(
                        context,
                        ANGRY + errorState.error.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        bi.reloadPostsBtn.setOnClickListener {
            moviesAdapter.refresh()
        }

        bi.swipeRefreshLayout.setOnRefreshListener {
            bi.swipeRefreshLayout.isRefreshing = false
            moviesAdapter.refresh()
        }
    }
}
