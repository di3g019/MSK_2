package com.example.msk2.retrofit

import com.example.msk2.retrofit.request.RequestLogin
import com.example.msk2.retrofit.request.RequestRegistro
import com.example.msk2.retrofit.response.ResponseLogin
import com.example.msk2.retrofit.response.ResponseRegistro
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MSKService {

    @POST("auth/login")
    fun login(@Body login:RequestLogin):
            Call<ResponseLogin>


    @POST("estiba")
    fun crearEstiba(@Body registro:RequestRegistro):
            Call<ResponseRegistro>

    @GET("")
    fun listarProgramacionPorDocumento()
}