package com.techja.themoviekotlin.api.res

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class SessionRes : Serializable {
    @SerializedName("success")
    var success = false
    @SerializedName("session_id")
    var sessionId: String? = null
}