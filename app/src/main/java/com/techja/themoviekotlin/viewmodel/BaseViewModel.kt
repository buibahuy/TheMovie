package com.techja.themoviekotlin.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.techja.themoviekotlin.OnAPICallBack
import com.techja.themoviekotlin.api.APIHelper
import com.techja.themoviekotlin.api.APIService
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        val TAG: String = BaseViewModel::class.java.name
    }

    protected var callBack: OnAPICallBack? = null

    fun setAPICallBack(callBack: OnAPICallBack) {
        this.callBack = callBack
    }

    protected fun <T> initHandleResponse(key: String): Callback<T> {
        return object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    assert(response.body() != null)
                    handleSuccess(key, response.body())
                } else {
                    handleFail(key, response.code(), response.errorBody())
                }
            }
            override fun onFailure(call: Call<T>, t: Throwable) {
                Log.e(TAG, "onFailure: " + t.message)
                handleException(key, t)
            }
        }
    }

    protected open fun handleException(key: String, t: Throwable) {
        callBack!!.apiError(key, 999, t.message)
    }

    protected open fun handleFail(key: String, code: Int, errorBody: ResponseBody?) {
        Log.e(TAG, "handleFail: $code")
        callBack!!.apiError(key, code, errorBody)
    }

    protected open fun <T> handleSuccess(key: String, body: T?) {
        callBack!!.apiSuccess(key, body)
    }
}