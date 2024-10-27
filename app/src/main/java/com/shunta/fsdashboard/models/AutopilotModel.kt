package com.shunta.fsdashboard.models

import com.google.gson.annotations.SerializedName

data class AutopilotModel(
    @SerializedName("hdg")         val hdg: Float,
    @SerializedName("alt")         val alt: Int,
    @SerializedName("masterState") val masterState: Boolean,
    @SerializedName("navState")    val navState: Boolean,
    @SerializedName("flcState")    val flcState: Boolean,
)
