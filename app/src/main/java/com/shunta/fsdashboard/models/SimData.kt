package com.shunta.fsdashboard.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class SimData (
    @SerializedName("frequencies")   val frequencies: List<Frequency>,
    @SerializedName("aircraft_info") val aircraftInfoModel: AircraftInfoModel,
    @SerializedName("autopilot")     val autopilot: AutopilotModel,
) {
    fun toJSON(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJSON(json: String): SimData {
            return Gson().fromJson(json, SimData::class.java)
        }
    }
}