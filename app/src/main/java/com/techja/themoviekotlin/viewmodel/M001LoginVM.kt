package com.techja.themoviekotlin.viewmodel

import android.util.Log
import com.techja.themoviekotlin.api.APIHelper
import com.techja.themoviekotlin.api.req.AccountReq
import com.techja.themoviekotlin.api.req.RequestTokenReq
import com.techja.themoviekotlin.api.res.model.AuthenRes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class M001LoginVM @Inject constructor(private val apiHelper: APIHelper) : BaseViewModel() {
    companion object {
        const val KEY_API_CREATE_SESSION_ID = "KEY_API_CREATE_SESSION_ID"
        private const val KEY_API_AUTHEN = "KEY_API_AUTHEN"
        private const val KEY_API_CREATE_SESSION = "KEY_API_CREATE_SESSION"
        val TAG: String = M001LoginVM::class.java.name
    }

    private var userName: String? = null
    private var password: String? = null

    fun getAuthen(userName: String, password: String) {
        this.userName = userName
        this.password = password
        apiHelper.getAuthen().enqueue(initHandleResponse(KEY_API_AUTHEN))
    }

    private fun createSession(requestToken: String) {
        apiHelper.createSession(AccountReq(userName!!, password!!, requestToken))
            .enqueue(
                initHandleResponse(KEY_API_CREATE_SESSION)
            )
    }

    private fun createSessionId(requestToken: String) {
        apiHelper.createSessionID(RequestTokenReq(requestToken)).enqueue(
            initHandleResponse(
                KEY_API_CREATE_SESSION_ID
            )
        )
    }

    override fun <T> handleSuccess(key: String, body: T?) {
        Log.i(TAG, "handleSuccess: $key")
        Log.i(TAG, "handleSuccess: $body")
        when (key) {
            KEY_API_AUTHEN -> createSession((body as AuthenRes).requestToken!!)
            KEY_API_CREATE_SESSION -> createSessionId((body as AuthenRes).requestToken!!)
            KEY_API_CREATE_SESSION_ID -> callBack!!.apiSuccess(key, body)
        }
    }
}