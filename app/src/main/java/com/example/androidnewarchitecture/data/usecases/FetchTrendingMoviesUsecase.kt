package com.example.androidnewarchitecture.data.usecases

import com.example.androidnewarchitecture.data.repository.MovieDBRepository
import javax.inject.Inject

class FetchTrendingMoviesUseCase @Inject constructor(private val repository: MovieDBRepository) {
    suspend operator fun invoke(
        pageNum: Int = 1
    ) = repository.fetchTrendingMoviesList(
        pageNumber = pageNum
    )
}
