package com.techja.themoviekotlin

interface OnAPICallBack {
    fun apiSuccess(key: String, data: Any?)
    fun apiError(key: String, code: Int, data: Any?)
}