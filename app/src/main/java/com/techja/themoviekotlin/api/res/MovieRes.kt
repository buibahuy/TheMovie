package com.techja.themoviekotlin.api.res

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MovieRes : Serializable {
    @SerializedName("page")
    var expiresAt: Int? = null

    @SerializedName("total_page")
    var totalPage: Int? = null

    @SerializedName("results")
    var results: List<Result>? = null

    inner class Result : Serializable {

        @SerializedName("id")
        var id: Int? = null

        @SerializedName("original_title")
        var title: String? = null

        @SerializedName("overview")
        var overview: String? = null

        @SerializedName("poster_path")
        var posterPath: String? = null

        @SerializedName("release_date")
        var releaseDate: String? = null

    }
}