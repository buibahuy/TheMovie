package com.techja.themoviekotlin

import android.content.Context
import android.content.SharedPreferences
import retrofit2.Response

class CommonUtils private constructor() {
    companion object {
        private var instance: CommonUtils? = null
        const val PREF_FILE = "pref_saving"

        fun getInstance(): CommonUtils {
            if (instance == null) {
                instance = CommonUtils()
            }
            return instance!!
        }
    }


    fun savePref(key: String?, value: String?) {
        val pref: SharedPreferences =
            App.instance.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        pref.edit().putString(key, value).apply()
    }

    fun getPref(key: String?): String? {
        val pref: SharedPreferences =
            App.instance.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        return pref.getString(key, "")
    }

    fun clearPref(key: String?) {
        val pref: SharedPreferences =
            App.instance.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE)
        pref.edit().remove(key).apply()
    }

    fun <T> handleResponse(
        response: Response<T>,
        onSuccess: (data: T) -> Unit,
        onError: (msg: String) -> Unit
    ) {
        if (response.isSuccessful) {
            if (response.body() != null)
                onSuccess.invoke(response.body()!!)
            else onError.invoke(response.errorBody().toString())
        } else onError.invoke(response.errorBody().toString())
    }
}