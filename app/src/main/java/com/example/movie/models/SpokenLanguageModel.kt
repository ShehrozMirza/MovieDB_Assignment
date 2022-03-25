package com.example.movie.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpokenLanguageModel(
    @field:SerializedName("english_name")
    val englishName: String = "",
    @field:SerializedName("iso_639_1")
    val iso6391: String = "",
    @field:SerializedName("name")
    val name: String = ""
) : Parcelable

