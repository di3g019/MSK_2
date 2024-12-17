package com.example.msk2.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.msk2.retrofit.MSKCliente
import com.example.msk2.retrofit.request.RequestLogin
import com.example.msk2.retrofit.request.RequestRegistro
import com.example.msk2.retrofit.response.ResponseLogin
import com.example.msk2.retrofit.response.ResponseRegistro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {

    var loginResponse = MutableLiveData<ResponseLogin>()
    var registroResponse = MutableLiveData<ResponseRegistro>()

    fun autenticarEstiba(requestLogin: RequestLogin): MutableLiveData<ResponseLogin>{

        var call: Call<ResponseLogin> = MSKCliente.retrofitService.login(requestLogin)

        call.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(p0: Call<ResponseLogin>, p1: Response<ResponseLogin>) {
                loginResponse.value = p1.body()
            }

            override fun onFailure(p0: Call<ResponseLogin>, p1: Throwable) {
                Log.e("Error Api Login",
                    p1.message.toString())
            }
        })
        return loginResponse
    }

    fun registrarEstiba(requestRegistro: RequestRegistro):MutableLiveData<ResponseRegistro>{
        val call: Call<ResponseRegistro> = MSKCliente.retrofitService.crearEstiba(requestRegistro)
        call.enqueue(object : Callback<ResponseRegistro> {
            override fun onResponse(p0: Call<ResponseRegistro>, p1: Response<ResponseRegistro>) {
                registroResponse.value = p1.body()
                Log.i("INFO-API", p1.body().toString())
            }

            override fun onFailure(p0: Call<ResponseRegistro>, p1: Throwable) {
                Log.e("ERROR API REGISTRO",
                    p1.message.toString())
            }
        })
        return registroResponse

    }


}