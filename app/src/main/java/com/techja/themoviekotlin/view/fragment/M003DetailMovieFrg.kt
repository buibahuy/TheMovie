package com.techja.themoviekotlin.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.techja.themoviekotlin.api.res.model.DetailMovie
import com.techja.themoviekotlin.api.res.model.MovieRes
import com.techja.themoviekotlin.databinding.M003DetailMovieFrgBinding
import com.techja.themoviekotlin.viewmodel.M003DetailMovieVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class M003DetailMovieFrg : BaseFragment<M003DetailMovieFrgBinding, M003DetailMovieVM>() {
    companion object {
        val TAG: String = M003DetailMovieFrg::class.java.name
        const val PATH_IMAGE: String = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/%s"
    }

    override fun initViews() {
        val item: MovieRes.Result = mData as MovieRes.Result

        binding.apply {
            actionBar.icBack.setOnClickListener {
                callBack.backToPrevious()
            }

            icAddToList.setOnClickListener {
                viewModel.addToList()
            }

            icFavorite.setOnClickListener {
                viewModel.favoriteMovie()
            }

            icAddToWatchList.setOnClickListener {
                viewModel.addToWatchList()
            }

            icFavorite.setOnClickListener {
                viewModel.rateMovie()
            }
        }

        viewModel.getDetailMovie(item.id!!)
        setupUI()
    }


     private fun setupUI() {
        viewModel.movieDetail.observe(this){ res ->
            Glide.with(mContext).load(String.format(PATH_IMAGE, res.posterPath)).into(binding.ivAvt)
            Glide.with(mContext).load(String.format(PATH_IMAGE, res.backdropPath))
                .into(binding.ivBackground)

            binding.tvTitle.text = res.title

            val date: List<String> = res.releaseDate!!.split("-")
            val vnDate = date[2] + "/" + date[1] + "/" + date[0]
            binding.tvDate.text = String.format("%s(VN)", vnDate)
            binding.tvYear.text = String.format("(%s)",  date[0])

            val hour = res.runTime!! / 60
            val minute = res.runTime!! - hour * 60
            binding.tvTime.text = String.format("%sh %sm", hour, minute)

            val genres = StringBuilder()
            for (index in res.genres!!.indices) {
                genres.append(res.genres!![index].name)
                    .append(if (index < res.genres!!.size - 1) ", " else "")
            }

            binding.tvGenres.text = genres
            binding.tvOverView.text = res.overview

            binding.progressPercent.progress = (res.voteAverage!! * 10).toInt()
            binding.tvPercent.text = String.format("%s%%", (res.voteAverage!! * 10).toInt())
        }

    }


    override fun getClassViewModel(): Class<M003DetailMovieVM> = M003DetailMovieVM::class.java

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): M003DetailMovieFrgBinding = M003DetailMovieFrgBinding.inflate(inflater, container, false)
}