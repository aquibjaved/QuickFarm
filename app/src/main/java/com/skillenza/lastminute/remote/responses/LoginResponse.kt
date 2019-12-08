package com.skillenza.lastminute.remote.responses

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("_id") var userId: String,
    @SerializedName("name") var userName: String
)
