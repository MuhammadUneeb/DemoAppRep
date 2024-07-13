package com.example.demoapp.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale

@HiltAndroidApp
class App():Application() {
    override fun onCreate() {
        super.onCreate()
    }

}