package com.skillenza.lastminute.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class MyBooking(
    @SerializedName("_id") val id: String,
    @SerializedName("mname") val machineName: String,
    @SerializedName("mtype") val machineType: String,
    @SerializedName("mprice") val machinePrice: String
)
