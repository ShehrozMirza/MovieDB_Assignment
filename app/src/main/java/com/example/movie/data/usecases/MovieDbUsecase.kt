package com.example.movie.data.usecases

import com.example.movie.data.repository.MovieDBRepository
import javax.inject.Inject

class MovieDbUsecase @Inject constructor(private val repository: MovieDBRepository) {
    suspend fun fetchTrendingMovies(
        pageNum: Int = 1
    ) = repository.fetchTrendingMoviesList(
        pageNumber = pageNum
    )

    suspend fun fetchMovieDetail(
        id: Int
    ) = repository.getMovieDetail(
        id = id
    )
}
