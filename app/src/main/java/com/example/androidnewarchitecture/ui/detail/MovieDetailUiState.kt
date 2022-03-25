package com.example.androidnewarchitecture.ui.detail

sealed class MovieDetailUiState

object LoadingState : MovieDetailUiState()
object ContentState : MovieDetailUiState()
class ErrorState(val message: String) : MovieDetailUiState()
