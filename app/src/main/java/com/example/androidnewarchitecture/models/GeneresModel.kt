package com.example.androidnewarchitecture.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GeneresModel(
    @field:SerializedName("id")
    val id: Int = 0,
    @field:SerializedName("name")
    val name: String = ""
) : Parcelable

