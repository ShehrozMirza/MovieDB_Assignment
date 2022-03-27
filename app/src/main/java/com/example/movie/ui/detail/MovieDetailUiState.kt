package com.example.movie.ui.detail

sealed class MovieDetailUiState
object LoadingState : MovieDetailUiState()
object ContentState : MovieDetailUiState()
class ErrorState(val message: String) : MovieDetailUiState()
