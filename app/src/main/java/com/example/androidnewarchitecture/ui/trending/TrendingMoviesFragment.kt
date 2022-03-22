package com.example.androidnewarchitecture.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.androidnewarchitecture.R
import com.example.androidnewarchitecture.adapters.MoviesAdapter
import com.example.androidnewarchitecture.base.BaseFragment
import com.example.androidnewarchitecture.databinding.TrendingMoviesFragmentBinding
import com.example.androidnewarchitecture.utils.gone
import com.example.androidnewarchitecture.utils.showSnack
import com.example.androidnewarchitecture.utils.showToast
    import com.example.androidnewarchitecture.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendingMoviesFragment : BaseFragment<TrendingMoviesFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> TrendingMoviesFragmentBinding
        get() = TrendingMoviesFragmentBinding::inflate

    private val viewModel: TrendingMoviesViewModel by viewModels()

    lateinit var moviesAdapter: MoviesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        initObservations()
    }

    private fun setupViews() {
        // Movies RecyclerView
        moviesAdapter = MoviesAdapter { photo, _ ->

        }
        bi.recyclerTrendingMovies.adapter = moviesAdapter
        moviesAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        // NestedScrollView
        bi.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, _, scrollY, _, _ ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                viewModel.loadMoreMovies()
            }
        }
    }

    private fun initObservations() {
        viewModel.uiStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoadingState -> {
                    bi.recyclerTrendingMovies.gone()
                    bi.progressPhotos.visible()
                }

                is LoadingNextPageState -> {
                    bi.progressPhotos.gone()
                    showToast(getString(R.string.message_load_movies_str))
                }

                is ContentState -> {
                    bi.recyclerTrendingMovies.visible()
                    bi.progressPhotos.gone()
                }

                is ErrorState -> {
                    bi.progressPhotos.gone()
                    bi.nestedScrollView.showSnack(state.message, getString(R.string.action_retry_str)) {
                        viewModel.retry()
                    }
                }

                is ErrorNextPageState -> {
                    bi.nestedScrollView.showSnack(state.message, getString(R.string.action_retry_str)) {
                        viewModel.retry()
                    }
                }

                is EmptyState->{
                    bi.recyclerTrendingMovies.gone()
                    bi.progressPhotos.gone()
                }
            }
        }

        viewModel.moviesListLiveData.observe(viewLifecycleOwner) { photos ->
            moviesAdapter.updateItems(photos)
        }
    }
}
