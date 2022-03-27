package com.example.movie.utils.factory

import com.example.movie.models.MovieModel

interface DataFactory {
    /**
    get fake data
     */
    fun getMovieModel(): MovieModel
}