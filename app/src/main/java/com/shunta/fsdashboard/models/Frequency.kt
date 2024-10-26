package com.shunta.fsdashboard.models

import com.google.gson.annotations.SerializedName

data class Frequency (
    @SerializedName("id")      val id: Int,
    @SerializedName("name")    val name: String,
    @SerializedName("active")  val active: String,
    @SerializedName("standby") val standby: String
)