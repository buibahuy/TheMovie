package com.techja.themoviekotlin.api.req

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RequestTokenReq(reqToken: String) : Serializable {
    @SerializedName("request_token")
    val requestToken = reqToken
}