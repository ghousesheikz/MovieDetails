package com.sample.hiltdisampleapp.data.model

import com.google.gson.annotations.SerializedName

data class MoviePojo(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<Movies>?,
){
    data class Movies(
        @SerializedName("id") val id: Int,
        @SerializedName("original_language") val original_language: String?,
        @SerializedName("original_title") val original_title: String?,
        @SerializedName("poster_path") val poster_path: String?,
        @SerializedName("title") val title: String?
    )
}
