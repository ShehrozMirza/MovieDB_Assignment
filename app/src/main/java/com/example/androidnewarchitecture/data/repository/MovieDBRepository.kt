package com.example.androidnewarchitecture.data.repository

import com.example.androidnewarchitecture.data.DataState
import com.example.androidnewarchitecture.models.MovieListResponse
import kotlinx.coroutines.flow.Flow

interface MovieDBRepository {
    suspend fun fetchTrendingMoviesList(pageNumber: Int): Flow<DataState<MovieListResponse>>
}
