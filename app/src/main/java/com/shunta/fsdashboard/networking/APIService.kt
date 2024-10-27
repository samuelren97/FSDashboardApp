package com.shunta.fsdashboard.networking

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface APIService {
    @PUT("aircraft/radios/{id}/swap")
    fun putSwap(
        @Path("id") id: Int
    ): Call<ResponseBody>

    @PUT("aircraft/radios/{id}/standby")
    fun putStandby(
        @Path("id") id: Int,
        @Body freq: String,
    ): Call<ResponseBody>
}