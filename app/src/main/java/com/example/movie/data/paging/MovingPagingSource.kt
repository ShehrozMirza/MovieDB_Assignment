package com.example.movie.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movie.data.DataState
import com.example.movie.data.usecases.TrendingMoviesUsecase
import com.example.movie.models.MovieModel
import com.example.movie.utils.AppConstants
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(private val usecase: TrendingMoviesUsecase) :
    PagingSource<Int, MovieModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val position = params.key ?: AppConstants.POST_STARTING_PAGE_INDEX
            var response: List<MovieModel> = listOf()

            usecase.fetchTrendingMovies(pageNum = position).collect { dataState ->
                if (dataState is DataState.Success) {
                    response = dataState.data.movies
                }
            }

            val prevKey = if (position == AppConstants.POST_STARTING_PAGE_INDEX) {
                null
            } else {
                position - 1
            }
            val nextKey = if (response.isEmpty()) {
                null
            } else {
                position + 1
            }

            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}