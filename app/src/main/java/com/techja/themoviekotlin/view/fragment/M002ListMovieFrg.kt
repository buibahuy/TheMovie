package com.techja.themoviekotlin.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.techja.themoviekotlin.api.res.model.MovieRes
import com.techja.themoviekotlin.databinding.M002ListMovieFrgBinding
import com.techja.themoviekotlin.view.adapter.MovieAdapter
import com.techja.themoviekotlin.viewmodel.M002ListMovieVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class M002ListMovieFrg : BaseFragment<M002ListMovieFrgBinding, M002ListMovieVM>() {
    private lateinit var mAdapter: MovieAdapter

    companion object {
        val TAG: String = M002ListMovieFrg::class.java.name
    }

    override fun initViews() {
        viewModel.getListMovie()
        handleListMovie()
        binding.rvListMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getListMovie()
                    handleListMovie()
                }
            }
        })
    }


     fun handleListMovie() {
        mAdapter = MovieAdapter(viewModel.resultList.value ?: listOf(), mContext)
        binding.rvListMovie.adapter = mAdapter
        mAdapter.getItemResult().observe(this) {
            handleItemResult(it)
        }
         viewModel.resultList.observe(this){
             mAdapter.updateListResult(it)
         }
    }

    private fun handleItemResult(result: MovieRes.Result) {
        callBack.showFragment(M003DetailMovieFrg.TAG, result, true)
    }


    override fun getClassViewModel(): Class<M002ListMovieVM> = M002ListMovieVM::class.java

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): M002ListMovieFrgBinding = M002ListMovieFrgBinding.inflate(inflater, container, false)
}