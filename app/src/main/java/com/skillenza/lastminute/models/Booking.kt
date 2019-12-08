package com.skillenza.lastminute.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Booking(
    @SerializedName("_id") val id: String,
    @SerializedName("userid") val userId: String,
    @SerializedName("mname") val machineName: String,
    @SerializedName("machine") val machineId: String,
    @SerializedName("booking") val date: Date
)
