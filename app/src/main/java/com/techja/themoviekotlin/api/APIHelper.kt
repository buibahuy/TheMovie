package com.techja.themoviekotlin.api

import com.techja.themoviekotlin.api.req.AccountReq
import com.techja.themoviekotlin.api.req.RequestTokenReq
import com.techja.themoviekotlin.api.res.model.AuthenRes
import com.techja.themoviekotlin.api.res.model.DetailMovie
import com.techja.themoviekotlin.api.res.model.MovieRes
import com.techja.themoviekotlin.api.res.model.SessionRes
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import kotlinx.coroutines.flow.Flow
interface APIHelper {

    fun getAuthen(): Call<AuthenRes?>

    fun createSession( acc: AccountReq): Call<AuthenRes?>

    fun createSessionID( tokenReq: RequestTokenReq): Call<SessionRes?>

    suspend fun getListMovie(page: Int): Response<MovieRes>

    suspend fun getDetailMovie(id: Int): Response<DetailMovie>

}