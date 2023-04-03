package com.techja.themoviekotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.techja.themoviekotlin.CommonUtils
import com.techja.themoviekotlin.api.APIHelper
import com.techja.themoviekotlin.api.res.model.DetailMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class M003DetailMovieVM @Inject constructor(private val apiHelper: APIHelper) : BaseViewModel() {

    val movieDetail = MutableLiveData<DetailMovie>()
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
        viewModelScope.launch {
            CommonUtils.getInstance().handleResponse(
                apiHelper.getDetailMovie(id),
                onSuccess = {
                    movieDetail.value = it
                }, onError = {

                })
        }

//        apiHelper.getDetailMovie(id)
//            .enqueue(initHandleResponse(KEY_GET_DETAIL_MOVIE))
    }

    fun rateMovie(){

    }
}