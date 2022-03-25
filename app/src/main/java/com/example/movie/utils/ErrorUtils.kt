package com.example.movie.utils

import com.example.movie.models.ErrorResponseModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

object ErrorUtils {
    fun parseError(response: Response<*>): ErrorResponseModel {
        var errorResponse = ErrorResponseModel()
        response.errorBody()?.let {
            val type = object : TypeToken<ErrorResponseModel>() {}.type
            errorResponse = Gson().fromJson(it.charStream(), type)
        }
        return errorResponse
    }
}