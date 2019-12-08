package com.skillenza.lastminute.remote

import com.skillenza.lastminute.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

object HttpLoggingInterceptor {

    private var loggingInterceptor: HttpLoggingInterceptor? = null

    val instance: HttpLoggingInterceptor
        get() {
            if (loggingInterceptor == null) {
                loggingInterceptor = HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            }
            return loggingInterceptor as HttpLoggingInterceptor
        }
}
