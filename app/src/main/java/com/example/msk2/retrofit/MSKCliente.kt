package com.example.msk2.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MSKCliente {

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl("http://localhost:8080/wsmskestiba/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: MSKService by lazy {
        buildRetrofit().create(MSKService::class.java)
    }
}