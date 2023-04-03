package com.techja.themoviekotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.techja.themoviekotlin.CommonUtils
import com.techja.themoviekotlin.api.APIHelper
import com.techja.themoviekotlin.api.res.model.MovieRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class M002ListMovieVM  @Inject constructor(private val apiHelper : APIHelper): BaseViewModel() {

    companion object {
        val TAG: String = M002ListMovieVM::class.java.name
    }

     val resultList = MutableLiveData<List<MovieRes.Result>>(listOf())
    private var page: Int = 0

     fun getListMovie() {
        page++
        viewModelScope.launch {
            CommonUtils.getInstance().handleResponse(apiHelper.getListMovie(page),
            onSuccess = {
                resultList.value = resultList.value?.plus(it.results ?: emptyList())
            },
            onError = {

            })
        }
    }
}