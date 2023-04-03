package com.techja.themoviekotlin.api.res.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ListsRes: Serializable {
    @SerializedName("status_message")
    var statusMessage: String? = null

    @SerializedName("success")
    var success: Boolean = false

    @SerializedName("status_code")
    var statusCode: Int? = null

    @SerializedName("list_id")
    var listId: String? = null

}