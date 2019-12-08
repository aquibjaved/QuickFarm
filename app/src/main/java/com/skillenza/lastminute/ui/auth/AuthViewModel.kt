package com.skillenza.lastminute.ui.auth

import androidx.lifecycle.MutableLiveData
import com.skillenza.lastminute.remote.responses.LoginResponse
import com.skillenza.lastminute.ui.base.BaseViewModel
import com.skillenza.lastminute.utils.CustomRetrofitCallBack
import com.skillenza.lastminute.utils.PreferenceManager
import retrofit2.Call

class AuthViewModel: BaseViewModel() {

    val successLogin: MutableLiveData<Boolean> = MutableLiveData()

    private lateinit var loginCall: Call<LoginResponse>

    fun login(phoneNumber: String) {
        loginCall = dataManager.login(phoneNumber)
        addNetworkCall(loginCall)
        loginCall.enqueue(object: CustomRetrofitCallBack<LoginResponse> {
            override val TAG = this@AuthViewModel.TAG
            override fun onSuccess(response: LoginResponse) {
                PreferenceManager.userId = response.userId
                PreferenceManager.userName = response.userName
                successLogin.value = true
            }
            override fun onError(errorMessage: String) {
                message = errorMessage
                successLogin.value = false
            }
        })
    }
}