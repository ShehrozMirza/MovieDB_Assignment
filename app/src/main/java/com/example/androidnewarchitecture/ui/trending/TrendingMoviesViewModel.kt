package com.example.androidnewarchitecture.ui.trending

import androidx.lifecycle.*
import androidx.paging.*
import com.example.androidnewarchitecture.data.paging.MoviePagingSource
import com.example.androidnewarchitecture.data.usecases.MovieDbUsecase
import com.example.androidnewarchitecture.models.MovieModel
import com.example.androidnewarchitecture.utils.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TrendingMoviesViewModel @Inject constructor(
    private val moviesUseCase: MovieDbUsecase
) : ViewModel() {
    fun getMoviesList(): Flow<PagingData<MovieModel>> {
        return Pager(
            config = PagingConfig(pageSize = AppConstants.pageSize, maxSize = AppConstants.maxPageSize, enablePlaceholders = false),
            pagingSourceFactory = { MoviePagingSource(moviesUseCase) }
        ).flow.cachedIn(viewModelScope)
    }
}

