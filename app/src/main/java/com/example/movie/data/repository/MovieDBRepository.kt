package com.example.movie.data.repository

import com.example.movie.data.DataState
import com.example.movie.models.MovieListResponseModel
import com.example.movie.models.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieDBRepository {
    suspend fun fetchTrendingMoviesList(pageNumber: Int): Flow<DataState<MovieListResponseModel>>
    suspend fun getMovieDetail(id: Int): Flow<DataState<MovieModel>>
}
