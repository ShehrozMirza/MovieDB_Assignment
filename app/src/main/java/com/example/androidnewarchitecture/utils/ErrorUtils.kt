package com.example.androidnewarchitecture.utils

import com.example.androidnewarchitecture.models.ErrorResponseModel
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