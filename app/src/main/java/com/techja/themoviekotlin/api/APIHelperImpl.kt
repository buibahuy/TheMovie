package com.techja.themoviekotlin.api

import com.techja.themoviekotlin.api.req.AccountReq
import com.techja.themoviekotlin.api.req.RequestTokenReq
import com.techja.themoviekotlin.api.res.model.AuthenRes
import com.techja.themoviekotlin.api.res.model.DetailMovie
import com.techja.themoviekotlin.api.res.model.MovieRes
import com.techja.themoviekotlin.api.res.model.SessionRes
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class APIHelperImpl @Inject constructor(private  val apiService: APIService) : APIHelper {

    override fun getAuthen(): Call<AuthenRes?> = apiService.getAuthen()

    override fun createSession(acc: AccountReq): Call<AuthenRes?> =
        apiService.createSession(acc = acc)

    override fun createSessionID(tokenReq: RequestTokenReq): Call<SessionRes?> =
        apiService.createSessionID(tokenReq = tokenReq)

    override suspend fun getListMovie(page: Int): Response<MovieRes> = apiService.getListMovie(page = page)

    override suspend fun getDetailMovie(id: Int): Response<DetailMovie> = apiService.getDetailMovie(id = id)
}