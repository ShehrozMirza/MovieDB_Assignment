package com.example.androidnewarchitecture.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductionCompanyModel(
    @field:SerializedName("id")
    val id: Int = 0,
    @field:SerializedName("logo_path")
    val logoPath: String = "",
    @field:SerializedName("name")
    val name: String = "",
    @field:SerializedName("origin_country")
    val originCountry: String = ""
) : Parcelable

