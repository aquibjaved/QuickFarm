package com.skillenza.lastminute.ui.base

import androidx.lifecycle.ViewModel
import com.skillenza.lastminute.remote.DataManager
import retrofit2.Call

abstract class BaseViewModel: ViewModel() {

    val TAG = this.javaClass.simpleName
    lateinit var message: String
    val dataManager by lazy { DataManager() }

    private var networkCalls: MutableList<Call<*>> = ArrayList()

    fun addNetworkCall(networkCall: Call<*>) {
        networkCalls.remove(networkCall)
        networkCalls.add(networkCall)
    }

    override fun onCleared() {
        super.onCleared()

        networkCalls.forEach { call ->
            call.cancel()
        }
    }
}