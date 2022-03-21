package com.example.androidnewarchitecture.data.repository

import androidx.annotation.WorkerThread
import com.example.androidnewarchitecture.data.DataState
import com.example.androidnewarchitecture.data.remote.*
import com.example.androidnewarchitecture.models.MovieListResponse
import com.example.androidnewarchitecture.utils.StringUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class MovieDBRepositoryImpl @Inject constructor(
    private val stringUtils: StringUtils,
    private val apiService: MovieDBApiService
) : MovieDBRepository {

    @WorkerThread
    override suspend fun getMoviesList(
        pageNumber: Int
    ): Flow<DataState<MovieListResponse>> {
        return flow {
            apiService.getMoviesList(page = pageNumber).apply {
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
}
