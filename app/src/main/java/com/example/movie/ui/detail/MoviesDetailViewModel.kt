package com.example.movie.ui.detail

import androidx.lifecycle.*
import com.example.movie.data.DataState
import com.example.movie.data.usecases.MovieDbUsecase
import com.example.movie.models.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesDetailViewModel @Inject constructor(
    private val movieDbUsecase: MovieDbUsecase
) : ViewModel() {
    private var _uiState = MutableLiveData<MovieDetailUiState>()
    var uiStateLiveData: LiveData<MovieDetailUiState> = _uiState

    private var _movieDetail = MutableLiveData<MovieModel?>()
    val movieDetailLiveData: LiveData<MovieModel?> = _movieDetail

     fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            _uiState.postValue(LoadingState)
            movieDbUsecase.fetchMovieDetail(id).collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        _uiState.postValue(ContentState)
                        _movieDetail.postValue(dataState.data)
                    }

                    is DataState.Error -> {
                        _uiState.postValue(ErrorState(dataState.message))
                    }
                }
            }
        }
    }
}

