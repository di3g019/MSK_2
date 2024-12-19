package com.example.msk2.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.msk2.retrofit.MSKCliente
import com.example.msk2.retrofit.response.ResponseProgramacion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProgramacionRepository {

    private val responseProgramacion = MutableLiveData<List<ResponseProgramacion>>()
    private val errorMessage = MutableLiveData<String>()

    fun listarProgramaciones(nrodocumento:String) : MutableLiveData<List<ResponseProgramacion>>{
        val call: Call<List<ResponseProgramacion>> = MSKCliente.retrofitService.listarProgramacionPorDocumento(nrodocumento)
        call.enqueue(object : Callback<List<ResponseProgramacion>> {
            override fun onResponse(
                p0: Call<List<ResponseProgramacion>>,
                p1: Response<List<ResponseProgramacion>>
            ) {
                if (p1.isSuccessful){
                    responseProgramacion.postValue(p1.body()?: emptyList())
                }else{
                    Log.e("ERROR API PROGRAMACIONES", "Error código: ${p1.code()}")
                    errorMessage.postValue("Error en la respuesta: ${p1.message()}")
                    responseProgramacion.postValue(emptyList())
                }

            }

            override fun onFailure(p0: Call<List<ResponseProgramacion>>, p1: Throwable) {
                Log.e("ERROR API PROGRAMACIONES",
                    p1.message.toString())
            }
        })
        return responseProgramacion
    }

}