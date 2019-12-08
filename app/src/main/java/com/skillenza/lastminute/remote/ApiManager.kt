package com.skillenza.lastminute.remote

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    val service: Service

    companion object {
        private var apiManager: ApiManager? = null

        val instance: ApiManager
            get() {
                if (apiManager == null) {
                    apiManager = ApiManager()
                }
                return apiManager as ApiManager
            }
    }

    init {

        val okHttpBuilder = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor.instance)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://hackathon45.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setDateFormat("yyyy-MM-dd").create()))
            .client(okHttpBuilder.build())
            .build()

        service = retrofit.create(Service::class.java)
    }
}
