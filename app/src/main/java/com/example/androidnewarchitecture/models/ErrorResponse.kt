package com.example.androidnewarchitecture.models

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @field:SerializedName("status_code")
    val statusCode: Int = 0,
    @field:SerializedName("status_message")
    val statusMessage: String = "",
    @field:SerializedName("success")
    val success: Boolean = false
)