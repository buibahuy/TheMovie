package com.techja.themoviekotlin.viewmodel

class M003DetailMovieVM : BaseViewModel() {


    companion object {
        val TAG: String = M003DetailMovieVM::class.java.name
        private const val KEY_GET_DETAIL_MOVIE = "KEY_GET_DETAIL_MOVIE"
    }

    fun getDetailMovie(id: Int) {
        getAPI().getDetailMovie(id)
            .enqueue(initHandleResponse(M003DetailMovieVM.KEY_GET_DETAIL_MOVIE))
    }


}