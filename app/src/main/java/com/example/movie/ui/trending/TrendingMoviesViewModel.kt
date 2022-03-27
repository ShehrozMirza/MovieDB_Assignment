package com.example.movie.ui.trending

import androidx.lifecycle.*
import androidx.paging.*
import com.example.movie.data.paging.MoviePagingSource
import com.example.movie.data.usecases.TrendingMoviesUsecase
import com.example.movie.models.MovieModel
import com.example.movie.utils.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingMoviesViewModel @Inject constructor(
    private val moviesUseCase: TrendingMoviesUsecase
) : ViewModel() {

    val trendingMovies: LiveData<PagingData<MovieModel>>

    init {
        trendingMovies = fetchMoviesList()
    }

    fun fetchMoviesList(): LiveData<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = AppConstants.pageSize,
                maxSize = AppConstants.maxPageSize,
                enablePlaceholders = false
            ), pagingSourceFactory = { MoviePagingSource(usecase = moviesUseCase) }
        ).liveData.cachedIn(viewModelScope)
    }
}

