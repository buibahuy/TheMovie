package com.techja.themoviekotlin.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.techja.themoviekotlin.OnAPICallBack
import com.techja.themoviekotlin.R
import com.techja.themoviekotlin.view.OnMainCallBack
import com.techja.themoviekotlin.viewmodel.BaseViewModel

abstract class BaseFragment<V : ViewBinding, M : BaseViewModel> : Fragment(), View.OnClickListener, OnAPICallBack {
    protected lateinit var mContext: Context
    protected lateinit var binding: V
    protected lateinit var viewModel: M
    protected lateinit var callBack: OnMainCallBack
    protected var mData: Any? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = initViewBinding(inflater, container)
        viewModel = ViewModelProvider(this)[getClassViewModel()]
        viewModel.setAPICallBack(this)
        initViews()
        return binding.root
    }

    override fun onClick(v: View) {
        v.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.abc_fade_in))
        clickView(v)
    }

    protected open fun clickView(v: View) {
        //do nothing
    }

    fun setOnCallBack(callBack: OnMainCallBack) {
        this.callBack = callBack
    }

    fun setData(data: Any?) {
        mData = data
    }

    abstract fun initViews()
    abstract fun getClassViewModel(): Class<M>
    abstract fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): V

    override fun apiSuccess(key: String, data: Any?) {
        //do nothing
    }

    override fun apiError(key: String, code: Int, data: Any?) {
        if (code == 401) {
            callBack.showFragment(M001LoginFrg.TAG, null, false)
            Toast.makeText(context, R.string.txt_err_expired_login, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Error: $code, $data", Toast.LENGTH_SHORT).show()
        }
    }
}