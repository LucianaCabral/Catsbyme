package com.lcabral.catsbyme

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BreedApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        BuildConfig.DEBUG
    }
}
