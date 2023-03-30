package com.techja.themoviekotlin.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.techja.themoviekotlin.CommonUtils
import com.techja.themoviekotlin.R
import com.techja.themoviekotlin.api.res.model.SessionRes
import com.techja.themoviekotlin.databinding.M001LoginFrgBinding
import com.techja.themoviekotlin.viewmodel.M001LoginVM

class M001LoginFrg : BaseFragment<M001LoginFrgBinding, M001LoginVM>() {
    companion object {
        val TAG: String = M001LoginFrg::class.java.name
         const val KEY_SESSION_ID = "KEY_SESSION_ID"
    }

    override fun initViews() {
        binding.btLogin.setOnClickListener {
            viewModel.getAuthen(
                binding.edtUserName.text.toString(),
                binding.edtPassword.text.toString()
            )
        }
    }

    override fun getClassViewModel(): Class<M001LoginVM> = M001LoginVM::class.java

    override fun initViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): M001LoginFrgBinding = M001LoginFrgBinding.inflate(inflater, container, false)

    override fun apiError(key: String, code: Int, data: Any?) {
        if (code == 401) {
            Toast.makeText(context, R.string.txt_error_login_fail, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Error: $code, $data", Toast.LENGTH_SHORT).show()
        }
    }

    override fun apiSuccess(key: String, data: Any?) {
        if (key == M001LoginVM.KEY_API_CREATE_SESSION_ID) {
            val res: SessionRes = data as SessionRes
            CommonUtils.getInstance().savePref(KEY_SESSION_ID, res.sessionId)
            Toast.makeText(context, "Login is successfully", Toast.LENGTH_SHORT).show()
            callBack.showFragment(M002ListMovieFrg.TAG, null, false)
        }
    }
}