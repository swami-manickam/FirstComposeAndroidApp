package com.mycompose.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ComposeKoinApplication :Application(){


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ComposeKoinApplication)
            androidLogger()
            modules()
        }

    }
}