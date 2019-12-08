package com.skillenza.lastminute.remote.requests

import com.google.gson.annotations.SerializedName

data class BookingRequest(
    @SerializedName("userid") val userId: String,
    @SerializedName("mid") val machineId: String,
    @SerializedName("mname") val machineName: String,
    @SerializedName("bdate") val bookingDate: String)
