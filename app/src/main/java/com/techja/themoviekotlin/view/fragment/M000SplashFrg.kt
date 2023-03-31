package com.techja.themoviekotlin.view.fragment

import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.techja.themoviekotlin.CommonUtils
import com.techja.themoviekotlin.databinding.M000SplashFrgBinding
import com.techja.themoviekotlin.view.fragment.M001LoginFrg.Companion.KEY_SESSION_ID
import com.techja.themoviekotlin.viewmodel.CommonVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class M000SplashFrg : BaseFragment<M000SplashFrgBinding, CommonVM>() {
    companion object {
        val TAG: String = M000SplashFrg::class.java.name
    }

    override fun initViews() {
        Log.i(TAG, "initViews...")
        Handler().postDelayed({
            val sessionID: String = CommonUtils.getInstance().getPref(KEY_SESSION_ID).toString()
            if (sessionID == null) {
                gotoMainScreen()
            } else
                callBack.showFragment(M002ListMovieFrg.TAG, null, false)
        }, 2000)
    }

    private fun gotoMainScreen() {
        callBack.showFragment(M001LoginFrg.TAG, null, false)
    }

    override fun getClassViewModel(): Class<CommonVM> = CommonVM::class.java

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): M000SplashFrgBinding = M000SplashFrgBinding.inflate(inflater, container, false)
}