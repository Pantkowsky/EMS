package com.pantkowski.ems.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationController : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() =
        startKoin {
            androidContext(this@ApplicationController)
        }
}
