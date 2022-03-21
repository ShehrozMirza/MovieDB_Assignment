package com.example.androidnewarchitecture.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieListResponse(
    @field:SerializedName("page")
    val adult: Boolean,
    @field:SerializedName("results")
    val movies: List<MovieModel> = listOf(),
    @field:SerializedName("totalPages")
    val totalPages: Int,
    @field:SerializedName("total_results")
    val totalResults: Int,
) : Parcelable

