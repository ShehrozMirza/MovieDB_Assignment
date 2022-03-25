package com.example.movie.data.remote

import com.example.movie.models.MovieListResponseModel
import com.example.movie.models.MovieModel
import com.example.movie.utils.AppConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBApiService {

    @GET("discover/movie")
    suspend fun getMoviesList(
        @Query("api_key") apiKey: String = AppConstants.API_KEY,
        @Query("language") language: String = "en-Us",
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") page: Int = 1
    ): ApiResponse<MovieListResponseModel>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = AppConstants.API_KEY
    ): ApiResponse<MovieModel>

}
