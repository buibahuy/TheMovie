package com.techja.themoviekotlin.api.req

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AccountReq(userName: String, pass: String, reqToken: String) : Serializable {
    @SerializedName("username")
    val username = userName

    @SerializedName("password")
    val password = pass

    @SerializedName("request_token")
    val requestToken = reqToken
}