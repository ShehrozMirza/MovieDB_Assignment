
package com.example.androidnewarchitecture.ui.trending

sealed class TrendingMovieUiState

object LoadingState : TrendingMovieUiState()
object LoadingNextPageState : TrendingMovieUiState()
object ContentState : TrendingMovieUiState()
object ContentNextPageState : TrendingMovieUiState()
object EmptyState : TrendingMovieUiState()
class ErrorState(val message: String) : TrendingMovieUiState()
class ErrorNextPageState(val message: String) : TrendingMovieUiState()
