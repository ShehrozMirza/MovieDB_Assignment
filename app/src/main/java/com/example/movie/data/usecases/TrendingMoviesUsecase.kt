package com.example.movie.data.usecases

import com.example.movie.data.repository.MovieDBRepository
import javax.inject.Inject

class TrendingMoviesUsecase @Inject constructor(private val repository: MovieDBRepository) {
    suspend fun fetchTrendingMovies(
        pageNum: Int = 1
    ) = repository.fetchTrendingMoviesList(
        pageNumber = pageNum
    )
}
