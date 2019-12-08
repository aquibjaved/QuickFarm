package com.skillenza.lastminute

import android.app.Application
import android.content.Context

class ThisApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object {

        lateinit var instance: ThisApplication

        val application: ThisApplication
            get() = instance

        val context: Context
            get() = instance.applicationContext
    }
}
