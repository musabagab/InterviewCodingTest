package com.musabagab.interviewtest

import android.app.Application
import com.facebook.stetho.Stetho

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}