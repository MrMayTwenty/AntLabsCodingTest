package com.example.antlabs

import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiClass {
    @GET("08692241-6230-4900-9506-728b7e6699f8/users")
    fun getData(@Query("apikey") apiKey: String): Call<ApiResponse>
}