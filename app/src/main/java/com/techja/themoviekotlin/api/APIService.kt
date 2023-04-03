package com.techja.themoviekotlin.api

import com.techja.themoviekotlin.api.req.AccountReq
import com.techja.themoviekotlin.api.req.RequestListReq
import com.techja.themoviekotlin.api.req.RequestTokenReq
import com.techja.themoviekotlin.api.res.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {
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
    suspend fun getListMovie(@Query("page") page: Int): Response<MovieRes>

    @GET("movie/{id}?api_key=$API_KEY")
    @Headers("Content-Type: application/json")
    suspend fun getDetailMovie(@Path("id") id: Int): Response<DetailMovie>

    @GET("list?api_key=$API_KEY&session_id={session_id}")
    @Headers("Content-Type: application/json;charset=utf-8")
    suspend fun createMovieList(@Path("session_id") sessionId:String,@Body listReq: RequestListReq): Response<ListsRes>

}