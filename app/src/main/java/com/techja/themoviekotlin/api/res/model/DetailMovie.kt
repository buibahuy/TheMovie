package com.techja.themoviekotlin.api.res.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DetailMovie : Serializable {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("vote_average")
    var voteAverage: Double? = null

    @SerializedName("release_date")
    var releaseDate: String? = null

    @SerializedName("runtime")
    var runTime: Int? = null

    @SerializedName("genres")
    var genres: List<Genres>? = null

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("poster_path")
    var posterPath: String? = null

    inner class Genres : Serializable {
        @SerializedName("id")
        var id: Int? = null

        @SerializedName("name")
        var name: String? = null
    }

}