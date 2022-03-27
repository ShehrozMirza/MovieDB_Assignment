package com.example.movie.utils

object AppConstants {
    const val TIME_OUT_OKHTTP_REQUEST = 30L
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "c9856d0cb57c3f14bf75bdc6c063b8f3"
    const val ANGRY = "\uD83D\uDE28 "
    const val POST_STARTING_PAGE_INDEX = 1
    private const val MOVIE_DB_IMAGE_BASE_URL = "https://image.tmdb.org/t/p"
    const val MOVIE_DB_IMAGE_SMALL_SIZE_URL = "${MOVIE_DB_IMAGE_BASE_URL}/w185/"
    const val MOVIE_DB_IMAGE_LARGE_SIZE_URL = "https://image.tmdb.org/t/p/w500/"
    const val pageSize = 20
    const val maxPageSize = pageSize * 3
}
