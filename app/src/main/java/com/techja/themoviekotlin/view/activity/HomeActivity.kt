package com.techja.themoviekotlin.view.activity

import com.techja.themoviekotlin.databinding.ActivityHomeBinding
import com.techja.themoviekotlin.view.fragment.M000SplashFrg
import com.techja.themoviekotlin.viewmodel.CommonVM
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, CommonVM>() {

    override fun backToPrevious() {
        onBackPressed()
    }

    override fun initViews() {
        showFragment(M000SplashFrg.TAG, null, false)
    }

    override fun initViewBinding(): ActivityHomeBinding =
        ActivityHomeBinding.inflate(layoutInflater)

    override fun initViewModel(): Class<CommonVM> = CommonVM::class.java
}