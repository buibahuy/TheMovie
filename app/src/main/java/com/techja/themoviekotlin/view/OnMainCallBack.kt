package com.techja.themoviekotlin.view

interface OnMainCallBack {
    fun showFragment(tag: String, data: Any?, isBack: Boolean)
    fun backToPrevious()
}