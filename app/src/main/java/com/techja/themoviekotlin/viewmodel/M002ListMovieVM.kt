package com.techja.themoviekotlin.viewmodel

import com.techja.themoviekotlin.api.APIHelper
import com.techja.themoviekotlin.api.res.model.MovieRes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class M002ListMovieVM  @Inject constructor(private val apiHelper : APIHelper): BaseViewModel() {

    companion object {
        val TAG: String = M002ListMovieVM::class.java.name
        private const val KEY_GET_LIST_MOVIE = "KEY_GET_LIST_MOVIE"
    }

    private val resultList = ArrayList<MovieRes.Result>()
    private var page: Int = 0

    fun getListMovie() {
        page++
        apiHelper.getListMovie(page).enqueue(initHandleResponse(KEY_GET_LIST_MOVIE))
    }

    fun addToResultList(results: List<MovieRes.Result>) {
        resultList.addAll(results)
    }

    fun getResultList(): List<MovieRes.Result> {
        return resultList
    }

}