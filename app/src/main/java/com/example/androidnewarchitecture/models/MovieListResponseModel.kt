package com.example.androidnewarchitecture.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieListResponseModel(
    @field:SerializedName("page")
    val page: Int = 0,
    @field:SerializedName("results")
    val movies: List<MovieModel> = listOf(),
    @field:SerializedName("total_pages")
    val totalPages: Int = 0,
    @field:SerializedName("total_results")
    val totalResults: Int = 0
) : Parcelable

