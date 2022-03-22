package com.example.androidnewarchitecture.ui.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidnewarchitecture.data.DataState
import com.example.androidnewarchitecture.data.usecases.FetchTrendingMoviesUseCase
import com.example.androidnewarchitecture.models.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingMoviesViewModel @Inject constructor(
    private val fetchTrendingMoviesUseCase: FetchTrendingMoviesUseCase
) : ViewModel() {

    private var _uiState = MutableLiveData<TrendingMovieUiState>()
    var uiStateLiveData: LiveData<TrendingMovieUiState> = _uiState

    private var _moviesList = MutableLiveData<List<MovieModel>>()
    var moviesListLiveData: LiveData<List<MovieModel>> = _moviesList

    private var pageNumber = 1

    init {
        fetchMovies(pageNumber)
    }

    fun loadMoreMovies() {
        pageNumber++
        fetchMovies(pageNumber)
    }

    fun retry() {
        fetchMovies(pageNumber)
    }

    private fun fetchMovies(page: Int) {
        _uiState.postValue(
            if (page == 1) {
                LoadingState
            } else {
                LoadingNextPageState
            }
        )
        viewModelScope.launch {
            fetchTrendingMoviesUseCase(page).collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        if (page == 1) {
                            // First page
                            _uiState.postValue(ContentState)
                            _moviesList.postValue(dataState.data.movies)
                        } else {
                            // Any other page
                            _uiState.postValue(ContentNextPageState)
                            val currentList = arrayListOf<MovieModel>()
                            _moviesList.value?.let { currentList.addAll(it) }
                            currentList.addAll(dataState.data.movies)
                            _moviesList.postValue(currentList)
                        }
                    }

                    is DataState.Error -> {
                        if (page == 1) {
                            _uiState.postValue(ErrorState(dataState.message))
                        } else {
                            _uiState.postValue(ErrorNextPageState(dataState.message))
                        }
                    }
                }
            }
        }
    }

}
