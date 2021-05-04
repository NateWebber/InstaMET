package com.nwebber.instamet

import android.app.Application

class InstaMetApp : Application() {

    override fun onCreate() {
        instance = this
        super.onCreate()
    }

    companion object{
        private lateinit var instance: InstaMetApp
        const val APP_VERSION = 1.0
    }
}