package com.shunta.fsdashboard.models

import com.google.gson.annotations.SerializedName

class Speeds (
    @SerializedName("ias") val ias: Int,
    @SerializedName("gs") val gs: Int,
    @SerializedName("tas") val tas: Int
)