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
    private const val API_BASE_URL: String = "http://192.168.2.12:5000/api/"


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
        val call = apiService.putSwap(id)

        call.enqueue(object: Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: retrofit2.Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    Log.d("RESTAPI", "Successful ${response.code()}")
                    ifSuccess()
                } else {
                    Log.e("RESTAPI", "response unsuccessful ${response.code()}")
                    val t: Exception = BadRequestException("Bad request while making call to API")
                    ifFail(t)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("RESTAPI", t.message.toString())
                ifFail(t)
            }
        })
    }

    fun setStandbyFreq(
        id: Int,
        freq: String,
        ifSuccess: () -> Unit,
        ifFail: (t: Throwable) -> Unit
    ) {
        val call = apiService.putStandby(id, freq)

        call.enqueue(object: Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("RESTAPI", "Successfull call ${response.code()}")
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