package com.techja.themoviekotlin.api

import com.techja.themoviekotlin.api.req.AccountReq
import com.techja.themoviekotlin.api.req.RequestTokenReq
import com.techja.themoviekotlin.api.res.model.AuthenRes
import com.techja.themoviekotlin.api.res.model.DetailMovie
import com.techja.themoviekotlin.api.res.model.MovieRes
import com.techja.themoviekotlin.api.res.model.SessionRes
import retrofit2.Call
import retrofit2.http.*

interface APIHelper {

    fun getAuthen(): Call<AuthenRes?>

    fun createSession(@Body acc: AccountReq): Call<AuthenRes?>

    fun createSessionID(@Body tokenReq: RequestTokenReq): Call<SessionRes?>

    fun getListMovie(@Query("page") page: Int): Call<MovieRes>

    fun getDetailMovie(@Path("id") id: Int): Call<DetailMovie>
}