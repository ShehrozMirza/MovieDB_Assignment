package com.example.androidnewarchitecture.utils

import com.example.androidnewarchitecture.models.ErrorResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

object ErrorUtils {
    fun parseError(response: Response<*>): ErrorResponse {
        var errorResponse = ErrorResponse()
        response.errorBody()?.let {
            val type = object : TypeToken<ErrorResponse>() {}.type
            errorResponse = Gson().fromJson(it.charStream(), type)
        }
        return errorResponse
    }
}