package com.shunta.fsdashboard.models

import com.google.gson.annotations.SerializedName

data class AircraftInfoModel (
    @SerializedName("alt")    val alt: Int,
    @SerializedName("hdg")    val hdg: Float,
    @SerializedName("speeds") val speeds: Speeds,
    @SerializedName("bar")    val bar: Float
)