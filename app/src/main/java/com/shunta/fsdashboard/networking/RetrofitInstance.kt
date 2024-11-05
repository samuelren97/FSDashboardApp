package com.shunta.fsdashboard.networking

import android.util.Log
import com.shunta.fsdashboard.exceptions.BadRequestException
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val API_BASE_URL: String = "http://10.0.2.2:5000/api/"


    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS) // Connection timeout
            .readTimeout(30, TimeUnit.SECONDS)    // Read timeout
            .writeTimeout(30, TimeUnit.SECONDS)   // Write timeout
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object ApiClient {
    private val apiService: APIService by lazy {
        RetrofitInstance.retrofit.create(APIService::class.java)
    }

    fun swapFreq(
        id: Int,
        ifSuccess: () -> Unit,
        ifFail: (t: Throwable) -> Unit,
    ) {
        call(
            apiService.putSwap(id),
            ifSuccess,
            ifFail
        )
    }

    fun setStandbyFreq(
        id: Int,
        freq: String,
        ifSuccess: () -> Unit,
        ifFail: (t: Throwable) -> Unit
    ) {
        call(
            apiService.putStandby(id, freq),
            ifSuccess,
            ifFail
        )
    }

    fun setAPHeading(
        hdg: Float,
        ifSuccess: () -> Unit,
        ifFail: (t: Throwable) -> Unit
    ) {
        call(
            apiService.putHdg(hdg),
            ifSuccess,
            ifFail
        )
    }

    fun setAPAlt(
        alt: Int,
        ifSuccess: () -> Unit,
        ifFail: (t: Throwable) -> Unit
    ) {
        call(
            apiService.putAlt(alt),
            ifSuccess,
            ifFail
        )
    }

    fun toggleAPMaster(
        ifSuccess: () -> Unit,
        ifFail: (t: Throwable) -> Unit
    ) {
        call(
            apiService.putMaster(),
            ifSuccess,
            ifFail
        )
    }

    fun toggleAPHdg(
        ifSuccess: () -> Unit,
        ifFail: (t: Throwable) -> Unit
    ) {
        call(
            apiService.postHdg(),
            ifSuccess,
            ifFail
        )
    }

    fun toggleAPNav(
        ifSuccess: () -> Unit,
        ifFail: (t: Throwable) -> Unit
    ) {
        call(
            apiService.putNav(),
            ifSuccess,
            ifFail
        )
    }

    fun toggleAPFlc(
        ifSuccess: () -> Unit,
        ifFail: (t: Throwable) -> Unit,
    ) {
        call(
            apiService.putFlc(),
            ifSuccess,
            ifFail
        )
    }

    private fun call(
        call: Call<ResponseBody>,
        ifSuccess: () -> Unit,
        ifFail: (t: Throwable) -> Unit
    ){
        call.enqueue(object: Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("RESTAPI", "Successful call ${response.code()}")
                if (response.isSuccessful) {
                    ifSuccess()
                } else {
                    val t: BadRequestException = BadRequestException("Bad request on API call")
                    ifFail(t)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("RESTAPI", t.message.toString())
                ifFail(t)
            }
        })
    }
}