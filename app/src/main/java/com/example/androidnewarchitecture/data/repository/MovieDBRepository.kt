package com.example.androidnewarchitecture.data.repository

import com.example.androidnewarchitecture.data.DataState
import com.example.androidnewarchitecture.models.MovieListResponseModel
import com.example.androidnewarchitecture.models.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieDBRepository {
    suspend fun fetchTrendingMoviesList(pageNumber: Int): Flow<DataState<MovieListResponseModel>>
    suspend fun getMovieDetail(id: Int): Flow<DataState<MovieModel>>
}
