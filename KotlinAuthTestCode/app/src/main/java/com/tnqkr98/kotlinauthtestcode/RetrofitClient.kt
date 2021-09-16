package com.tnqkr98.kotlinauthtestcode

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL:String = "~"
    private val retrofit:Retrofit.Builder by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val gosleepApiService: Network by lazy{
        retrofit.build().create(Network::class.java)
    }
}