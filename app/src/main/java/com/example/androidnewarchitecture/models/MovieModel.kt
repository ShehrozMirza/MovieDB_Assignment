package com.example.androidnewarchitecture.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    @field:SerializedName("adult")
    val adult: Boolean = false,
    @field:SerializedName("backdrop_path")
    val backdropPath: String? = "",
    @field:SerializedName("budget")
    val budget: Int? = 0,
    @field:SerializedName("genre_ids")
    val genreListId: List<Int> = listOf(),
    @field:SerializedName("genres")
    val generes: List<GeneresModel> = listOf(),
    @field:SerializedName("homepage")
    val homepage: String? = "",
    @field:SerializedName("id")
    val id: Int? = 0,
    @field:SerializedName("imdb_id")
    val imdbId: String? = "",
    @field:SerializedName("original_language")
    val originalLanguage: String = "",
    @field:SerializedName("original_title")
    val originalTitle: String? = "",
    @field:SerializedName("overview")
    val overview: String? = "",
    @field:SerializedName("popularity")
    val popularity: Double? = 0.0,
    @field:SerializedName("poster_path")
    val posterPath: String? = "",
    @field:SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyModel> = listOf(),
    @field:SerializedName("release_date")
    val releaseDate: String? = "",
    @field:SerializedName("status")
    val status: String? = "",
    @field:SerializedName("title")
    val title: String? = "",
    @field:SerializedName("video")
    val video: Boolean? = false,
    @field:SerializedName("vote_average")
    val voteAverage: Float? = 0.0f,
    @field:SerializedName("vote_count")
    val voteCount: Int? = 0,
    @field:SerializedName("runtime")
    val runTime: Int? = 0,
    @field:SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageModel> = listOf()
) : Parcelable

