package com.skillenza.lastminute.utils

import android.content.Context
import android.net.ConnectivityManager
import com.skillenza.lastminute.ThisApplication.Companion.context

object CommonUtils {

    val networkConnected: Boolean
        get() {
            val networkInfo = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
}