package com.skillenza.lastminute.utils

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.TimeoutException


interface CustomRetrofitCallBack<T>: Callback<T> {

    fun onSuccess(response: T)
    fun onError(errorMessage: String)
    val TAG: String

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            onSuccess(response.body()!!)
        } else {
            onError("Something went wrong")
            Log.e(TAG, response.message().toString())
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        when (t) {
            is IOException -> {
                if (!CommonUtils.networkConnected) {
                    onError("Please connect to internet")
                } else {
                    onError("Failed to connect to server")
                }
            }
            is TimeoutException -> {
                onError("Request timed out")
            }
            else -> {
                Log.e(TAG, t.localizedMessage)
                onError("Something went wrong")
            }
        }
    }
}
