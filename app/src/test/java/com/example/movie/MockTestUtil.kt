package com.example.movie

import com.example.movie.models.GeneresModel
import com.example.movie.models.MovieListResponseModel
import com.example.movie.models.MovieModel

class MockTestUtil {

    companion object {

        fun createMovieModelList(count: Int): List<MovieModel> {
            return (0 until count).map {
                MovieModel(
                    id = it,
                    title = "Shadows In Paradise",
                    releaseDate = "1980",
                    runTime = 90,
                    generes = createGeneres(),
                    overview = "An episode in the life of Nikander, a garbage man, involving the death of a co-worker, an affair and much more."
                )
            }
        }


        fun createMovieModelDetail(): MovieModel {
          return  MovieModel(
                id = 3,
                title = "Shadows In Paradise",
                releaseDate = "1980",
                runTime = 90,
                generes = createGeneres(),
                overview = "An episode in the life of Nikander, a garbage man, involving the death of a co-worker, an affair and much more."
            )
        }

        private fun createGeneres(): List<GeneresModel> {
            return listOf(
                GeneresModel(id = 1, name = "Drama"),
                GeneresModel(id = 2, name = "Comedy"),
                GeneresModel(id = 2, name = "Romance")
            )
        }

        fun createSearchPhotosResponse(moviesCount: Int = 5): MovieListResponseModel {
            return MovieListResponseModel(
                page = 1,
                totalPages = 3,
                movies = createMovieModelList(moviesCount),
                totalResults = 5
            )
        }
    }
}
