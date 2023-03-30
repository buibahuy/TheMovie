package com.techja.themoviekotlin.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TableRow
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techja.themoviekotlin.R
import com.techja.themoviekotlin.api.res.model.MovieRes

class MovieAdapter(private var listResult: List<MovieRes.Result>, private val context: Context) :
    RecyclerView.Adapter<MovieAdapter.MovieHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list_movie, parent, false)
        return MovieHolder(view)
    }

    private val itemResult: MutableLiveData<MovieRes.Result> = MutableLiveData()
    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val item: MovieRes.Result = listResult[position]
        holder.tvDate.text = item.releaseDate
        holder.tvDetail.text = item.overview
        holder.tvTitle.text = item.title
        holder.tvTitle.tag = item
        Glide.with(context).load(
            String.format(
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/%s",
                item.posterPath
            )
        ).into(holder.ivAvt)
    }

    override fun getItemCount(): Int {
        return listResult.size
    }

    fun updateListResult(resultList: List<MovieRes.Result>) {
        listResult = resultList
        notifyDataSetChanged()
    }

    inner class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivAvt = itemView.findViewById<ImageView>(R.id.iv_avt)!!
        var tvDate = itemView.findViewById<TextView>(R.id.tv_date)!!
        var tvDetail = itemView.findViewById<TextView>(R.id.tv_detail)!!
        var tvTitle = itemView.findViewById<TextView>(R.id.tv_title)!!

        init {
            itemView.findViewById<TableRow>(R.id.tr_movie).setOnClickListener(View.OnClickListener {
                it.startAnimation(
                    AnimationUtils.loadAnimation(
                        context,
                        androidx.appcompat.R.anim.abc_fade_in
                    )
                )
                showDetailMovie(tvTitle.tag)
            })
        }

    }

    private fun showDetailMovie(tag: Any) {
        itemResult.value = tag as (MovieRes.Result)
    }

    fun getItemResult(): MutableLiveData<MovieRes.Result> {
        return itemResult
    }
}