package com.example.movie.utils.factory

import com.example.movie.models.MovieModel
import javax.inject.Singleton

@Singleton
object DataFactoryImpl : DataFactory {

    override fun getMovieModel(): MovieModel {
        return MovieModel(
            id = 3
        )
    }
}

