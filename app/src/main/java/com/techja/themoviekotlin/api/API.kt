package com.techja.themoviekotlin.api

import com.techja.themoviekotlin.api.req.AccountReq
import com.techja.themoviekotlin.api.req.RequestTokenReq
import com.techja.themoviekotlin.api.res.AuthenRes
import com.techja.themoviekotlin.api.res.DetailMovie
import com.techja.themoviekotlin.api.res.MovieRes
import com.techja.themoviekotlin.api.res.SessionRes
import retrofit2.Call
import retrofit2.http.*

interface API {
    companion object {
        const val API_KEY = "fd99ca113db9db767e7710795700c3af"
    }

    @GET("authentication/token/new?api_key=$API_KEY")
    @Headers("Content-Type: application/json")
    fun getAuthen(): Call<AuthenRes?>

    @POST("authentication/token/validate_with_login?api_key=$API_KEY")
    @Headers("Content-Type: application/json")
    fun createSession(@Body acc: AccountReq): Call<AuthenRes?>

    @POST("authentication/session/new?api_key=$API_KEY")
    @Headers("Content-Type: application/json")
    fun createSessionID(@Body tokenReq: RequestTokenReq): Call<SessionRes?>

    @GET("discover/movie?api_key=$API_KEY")
    @Headers("Content-Type: application/json")
    fun getListMovie(@Query("page") page: Int): Call<MovieRes>

    @GET("movie/{id}?api_key=$API_KEY")
    @Headers("Content-Type: application/json")
    fun getDetailMovie(@Path("id") id: Int): Call<DetailMovie>


}