package com.example.movie.data.usecases

import com.example.movie.data.repository.MovieDBRepository
import javax.inject.Inject

class MoviesDetailsUsecase @Inject constructor(private val repository: MovieDBRepository) {
    suspend fun fetchMovieDetail(
        id: Int
    ) = repository.getMovieDetail(
        id = id
    )
}
