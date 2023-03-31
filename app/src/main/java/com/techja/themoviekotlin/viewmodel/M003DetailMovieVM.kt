package com.techja.themoviekotlin.viewmodel

import com.techja.themoviekotlin.api.APIHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class M003DetailMovieVM @Inject constructor(private val apiHelper: APIHelper) : BaseViewModel() {

    companion object {
        val TAG: String = M003DetailMovieVM::class.java.name
        private const val KEY_GET_DETAIL_MOVIE = "KEY_GET_DETAIL_MOVIE"
    }

    fun addToList(){

    }

    fun favoriteMovie(){

    }

    fun addToWatchList(){}


    fun getDetailMovie(id: Int) {
        apiHelper.getDetailMovie(id)
            .enqueue(initHandleResponse(KEY_GET_DETAIL_MOVIE))
    }

    fun rateMovie(){

    }
}