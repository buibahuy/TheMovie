package com.techja.themoviekotlin

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {
    lateinit var storage: Storage
    companion object{
        lateinit var instance:App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        storage = Storage()
    }
}