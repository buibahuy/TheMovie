package com.techja.themoviekotlin.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.techja.themoviekotlin.api.res.model.MovieRes
import com.techja.themoviekotlin.databinding.M002ListMovieFrgBinding
import com.techja.themoviekotlin.view.adapter.MovieAdapter
import com.techja.themoviekotlin.viewmodel.M002ListMovieVM
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class M002ListMovieFrg : BaseFragment<M002ListMovieFrgBinding, M002ListMovieVM>() {
    private lateinit var mAdapter: MovieAdapter

    companion object {
        val TAG: String = M002ListMovieFrg::class.java.name
    }

    override fun initViews() {
        viewModel.getListMovie()
//        binding.rvListMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    viewModel.getListMovie()
//                }
//            }
//        })
    }


    override fun apiSuccess(key: String, data: Any?) {
        val movieRes: MovieRes = data as MovieRes

        viewModel.addToResultList(movieRes.results!!)
        mAdapter = MovieAdapter(viewModel.getResultList(), mContext)
        binding.rvListMovie.adapter = mAdapter
        binding.rvListMovie.hasFixedSize()


        mAdapter.getItemResult().observe(this) {
            handleItemResult(it)
        }
        mAdapter.updateListResult(viewModel.getResultList())

        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,0){
            override fun onMove(
                recyclerView: RecyclerView,
                soure: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                val sourcePosition = soure.adapterPosition
                val targetPosition = target.adapterPosition

                Collections.swap(viewModel.getResultList(),sourcePosition,targetPosition)
                mAdapter.notifyItemMoved(sourcePosition,targetPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                TODO("Not yet implemented")
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.rvListMovie)
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