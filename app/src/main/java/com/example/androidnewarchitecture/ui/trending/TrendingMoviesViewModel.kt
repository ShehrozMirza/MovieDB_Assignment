package com.example.androidnewarchitecture.ui.trending

import androidx.lifecycle.*
import androidx.paging.*
import com.example.androidnewarchitecture.data.paging.MoviePagingSource
import com.example.androidnewarchitecture.data.usecases.FetchTrendingMoviesUseCase
import com.example.androidnewarchitecture.models.MovieModel
import com.example.androidnewarchitecture.utils.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TrendingMoviesViewModel @Inject constructor(
    private val fetchTrendingMoviesUseCase: FetchTrendingMoviesUseCase
) : ViewModel() {
    fun getMoviesList(): Flow<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(pageSize = AppConstants.pageSize, maxSize = AppConstants.maxPageSize, enablePlaceholders = true),
            pagingSourceFactory = { MoviePagingSource(fetchTrendingMoviesUseCase) }
        ).flow.cachedIn(viewModelScope)
    }
}

