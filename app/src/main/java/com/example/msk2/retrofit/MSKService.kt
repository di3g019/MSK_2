package com.example.msk2.retrofit

import com.example.msk2.retrofit.request.RequestLogin
import com.example.msk2.retrofit.response.ResponseLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MSKService {

    @POST("login")
    fun login(@Body login:RequestLogin):
            Call<ResponseLogin>

    @GET("")
    fun listarProgramacionPorDocumento()
}