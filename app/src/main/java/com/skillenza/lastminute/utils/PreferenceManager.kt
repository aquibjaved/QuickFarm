package com.skillenza.lastminute.utils

import android.annotation.SuppressLint
import android.content.Context
import com.skillenza.lastminute.ThisApplication.Companion.context

object PreferenceManager {

    private const val APPLICATION_PREFERENCE = "app-preferences"
    private const val USER_ID = "user-id"
    private const val USER_NAME = "user-name"

    private val sharedPreferences = context.getSharedPreferences(APPLICATION_PREFERENCE, Context.MODE_PRIVATE)

    var userId: String
        get() = sharedPreferences.getString(USER_ID, "")!!
        @SuppressLint("ApplySharedPref")
        set(userId) {
            sharedPreferences.edit().putString(USER_ID, userId).commit()
        }

    var userName: String
        get() = sharedPreferences.getString(USER_NAME, "")!!
        @SuppressLint("ApplySharedPref")
        set(userName) {
            sharedPreferences.edit().putString(USER_NAME, userName).commit()
        }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}
