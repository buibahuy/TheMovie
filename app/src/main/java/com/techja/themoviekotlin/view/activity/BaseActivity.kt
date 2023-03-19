package com.techja.themoviekotlin.view.activity

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.techja.themoviekotlin.R
import com.techja.themoviekotlin.view.OnMainCallBack
import com.techja.themoviekotlin.view.fragment.BaseFragment

abstract class BaseActivity<V : ViewBinding, M : ViewModel> : AppCompatActivity(),
    View.OnClickListener, OnMainCallBack {
    protected lateinit var binding: V
    protected lateinit var viewModel: M

    override fun onCreate(data: Bundle?) {
        super.onCreate(data)
        binding = initViewBinding()
        viewModel = ViewModelProvider(this)[initViewModel()]
        setContentView(binding.root)
        initViews()
    }

    abstract fun initViews()

    abstract fun initViewBinding(): V

    abstract fun initViewModel(): Class<M>

    override fun onClick(v: View) {
        v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.abc_fade_in))
        clickView(v)
    }

    protected open fun clickView(v: View) {
        //do nothing
    }

    protected fun notify(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun notify(msg: Int) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun showFragment(tag: String, data: Any?, isBack: Boolean) {
        try {
            val clazz = Class.forName(tag) //Trỏ vào 1 fragment class
            val cons = clazz.getConstructor()
            val frg: BaseFragment<*, *> = cons.newInstance() as BaseFragment<*, *> //Tạo ra 1 thực thể từ lớp fragment
            frg.setData(data)
            frg.setOnCallBack(this)
            val trans = supportFragmentManager.beginTransaction()
            if (isBack) {
                trans.addToBackStack(null) //go back to previous screen
            }
            trans.setCustomAnimations(R.animator.fragment_fade_enter, R.animator.fragment_fade_exit)
            trans.replace(R.id.ln_main, frg, tag).commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}