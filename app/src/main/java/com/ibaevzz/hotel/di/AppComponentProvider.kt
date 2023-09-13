package com.ibaevzz.hotel.di

import com.ibaevzz.hotel.App

object AppComponentProvider {

    private lateinit var appComponent: AppComponent
    fun appComponent() = appComponent

    fun build(application: App) {
        appComponent = DaggerAppComponent.factory().create(application)
    }

}