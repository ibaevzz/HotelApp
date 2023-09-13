package com.ibaevzz.hotel

import android.app.Application
import com.ibaevzz.hotel.di.AppComponentProvider

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        AppComponentProvider.build(this)
    }
}