package com.techja.themoviekotlin.api.res

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AuthenRes : Serializable {
    @SerializedName("success")
    var success = false

    @SerializedName("expires_at")
    var expiresAt: String? = null

    @SerializedName("request_token")
    var requestToken: String? = null
}