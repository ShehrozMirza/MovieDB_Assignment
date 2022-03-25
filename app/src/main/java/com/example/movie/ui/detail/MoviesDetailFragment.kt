package com.example.movie.ui.detail

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.movie.R
import com.example.movie.base.BaseFragment
import com.example.movie.databinding.MovieDetailFragmentBinding
import com.example.movie.models.MovieModel
import com.example.movie.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesDetailFragment : BaseFragment<MovieDetailFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MovieDetailFragmentBinding
        get() = MovieDetailFragmentBinding::inflate

    private val viewModel: MoviesDetailViewModel by viewModels()

    private val args: MoviesDetailFragmentArgs by navArgs()
    private val movieDetail: MovieModel by lazy { args.movieDetails }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        viewModel.getMovieDetail(movieDetail.id!!)
        initObservations()
    }

    private fun initObservations() {
        viewModel.uiStateLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {

                is LoadingState -> {
                    bi.progressbarLoading.visible()
                }

                is ContentState -> {
                    bi.progressbarLoading.gone()
                }

                is ErrorState -> {
                    showToast(state.message)
                    bi.progressbarLoading.gone()
                }
            }
        }

        viewModel.movieDetailLiveData.observe(viewLifecycleOwner) { detailItem ->
            bi.apply {

                detailItem?.apply {
                    ivCover.load(AppConstants.MOVIE_DB_IMAGE_LARGE_SIZE_URL + posterPath) {
                        placeholder(R.color.color_box_background)
                        crossfade(true)
                    }
                    tvTitle.text = title
                    tvGenre.text = TextUtils.join(", ", generes.map { it.name })
                    tvRunningTime.text = runTime.toString().plus(" mins")
                    tvDescription.text = detailItem.overview
                }
            }
        }
    }
}
