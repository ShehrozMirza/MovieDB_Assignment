package com.example.movie.data.repository

import androidx.annotation.WorkerThread
import com.example.movie.data.DataState
import com.example.movie.data.remote.*
import com.example.movie.models.MovieListResponseModel
import com.example.movie.models.MovieModel
import com.example.movie.utils.StringUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieDBRepositoryImpl @Inject constructor(
    private val stringUtils: StringUtils,
    private val apiService: MovieDBApiService
) : MovieDBRepository {

    @WorkerThread
    override suspend fun fetchTrendingMoviesList(
        pageNumber: Int
    ): Flow<DataState<MovieListResponseModel>> = flow {
        apiService.getMoviesList(page = pageNumber).apply {
            this.onSuccessSuspend {
                data?.let {
                    emit(DataState.success(it))
                }
            }
            // handle the case when the API request gets an error response.
            // e.g. internal server error.
        }.onErrorSuspend {
            //emit(DataState.error(message()))
            // handle the case when the API request gets an exception response.
            // e.g. network connection error.
            throw HttpException(response)
        }.onExceptionSuspend {
            if (this.exception is IOException) {
                //emit(DataState.error(stringUtils.noNetworkErrorMessage()))
                throw IOException(stringUtils.noNetworkErrorMessage())
            } else {
                throw Exception(stringUtils.somethingWentWrong())
                //emit(DataState.error(stringUtils.somethingWentWrong()))
            }
        }
    }

    override suspend fun getMovieDetail(id: Int): Flow<DataState<MovieModel>> =
        flow {
            apiService.getMovieDetail(movieId = id).apply {
                this.onSuccessSuspend {
                    data?.let {
                        emit(DataState.success(it))
                    }
                }
                // handle the case when the API request gets an error response.
                // e.g. internal server error.
            }.onErrorSuspend {
                emit(DataState.error(message()))
                // handle the case when the API request gets an exception response.
                // e.g. network connection error.
            }.onExceptionSuspend {
                if (this.exception is IOException) {
                    emit(DataState.error(stringUtils.noNetworkErrorMessage()))
                } else {
                    emit(DataState.error(stringUtils.somethingWentWrong()))
                }
            }
        }

}

