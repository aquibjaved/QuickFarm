package com.skillenza.lastminute.models

import com.google.gson.annotations.SerializedName

data class Listing(
    @SerializedName("_id") val id: String,
    @SerializedName("mname") val machineName: String,
    @SerializedName("mtype") val machineType: String,
    @SerializedName("mprice") val mPrice: String,
    @SerializedName("mowner") val ownerId: String

)
