package com.shunta.fsdashboard.networking

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
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

    @PUT("ap/hdg")
    fun putHdg(@Body hdg: Float): Call<ResponseBody>

    @PUT("ap/alt")
    fun putAlt(@Body alt: Int): Call<ResponseBody>

    @PUT("ap/master")
    fun putMaster(): Call<ResponseBody>

    @POST("ap/hdg")
    fun postHdg(): Call<ResponseBody>

    @PUT("ap/nav")
    fun putNav(): Call<ResponseBody>

    @PUT("ap/flc")
    fun putFlc: Call<ResponseBody>
}