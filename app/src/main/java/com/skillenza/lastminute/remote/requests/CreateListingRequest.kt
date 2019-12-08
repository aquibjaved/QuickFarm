package com.skillenza.lastminute.remote.requests

import com.google.gson.annotations.SerializedName

data class CreateListingRequest(
    @SerializedName("mname") val machineName: String,
    @SerializedName("mowner") val ownerId: String,
    @SerializedName("mtype") val machineType: String,
    @SerializedName("mprice") val machinePrice: String
)
