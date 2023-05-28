package com.techja.themoviekotlin.view.fragment

import android.graphics.*
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.techja.themoviekotlin.R
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

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN , ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
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
                val position = viewHolder.adapterPosition
                if (direction == ItemTouchHelper.LEFT) {
                    //mAdapter.notifyItemChanged(position)
                    Toast.makeText(requireContext(), "Swipe left", Toast.LENGTH_LONG).show()
                } else if (direction == ItemTouchHelper.RIGHT) {
//                    mAdapter.notifyItemChanged(position)
                    Toast.makeText(requireContext(), "Swipe right", Toast.LENGTH_LONG).show()
                }
            }
            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float,
                                     actionState: Int, isCurrentlyActive: Boolean) {
                val p = Paint()
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView = viewHolder.itemView
                    val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                    val width = height / 3

                    if (dX < 0) {
                        //p.color = Color.RED
                        val background = RectF(itemView.right.toFloat() + dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
                        c.drawRect(background, p)
                        val icon = BitmapFactory.decodeResource(mContext.resources, R.drawable.ic_heart)
                        val margin = (dX / 5 - width) / 2
                        val iconDest = RectF(itemView.right.toFloat() + margin, itemView.top.toFloat() + width, itemView.right.toFloat() + (margin + width), itemView.bottom.toFloat() - width)
                        c.drawBitmap(icon, null, iconDest, p)
                    }
                    if (dX > 0) {
                        p.color = Color.BLUE
                        val background = RectF(itemView.left.toFloat(), itemView.top.toFloat(), itemView.left.toFloat() + dX, itemView.bottom.toFloat())
                        c.drawRect(background, p)
                        val icon = BitmapFactory.decodeResource(mContext.resources, R.drawable.ic_book_mark)
                        val margin = (dX / 5 - width) / 2
                        val iconDest = RectF(margin, itemView.top.toFloat() + width, margin + width, itemView.bottom.toFloat() - width)
                        c.drawBitmap(icon, null, iconDest, p)
                    }
                } else {
                    c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX / 5, dY, actionState, isCurrentlyActive)
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