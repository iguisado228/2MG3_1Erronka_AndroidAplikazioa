package com.example.androidapp.core.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val Api_helbidea = "http://10.0.2.2:5000/"

    private val loginEgiten by lazy {
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    private val httpEskaera by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loginEgiten)
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Api_helbidea)
            .client(httpEskaera)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
