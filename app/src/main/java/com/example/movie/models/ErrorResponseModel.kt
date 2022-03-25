package com.example.movie.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResponseModel(
    @field:SerializedName("status_code")
    val statusCode: Int = 0,
    @field:SerializedName("status_message")
    val statusMessage: String = "",
    @field:SerializedName("success")
    val success: Boolean = false
) : Parcelable