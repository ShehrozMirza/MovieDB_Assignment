package com.example.androidnewarchitecture.data.remote

import com.example.androidnewarchitecture.models.MovieListResponse
import com.example.androidnewarchitecture.utils.AppConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbApiService {
    @GET("discover/movie")
    suspend fun loadPhotos(
        @Query("api_key") apiKey: String = AppConstants.API_KEY,
        @Query("language") language: String = "en-Us",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int = 1
    ): ApiResponse<MovieListResponse>
}
