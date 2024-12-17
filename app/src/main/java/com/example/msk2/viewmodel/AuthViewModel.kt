package com.example.msk2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.msk2.repository.AuthRepository
import com.example.msk2.retrofit.request.RequestLogin
import com.example.msk2.retrofit.request.RequestRegistro
import com.example.msk2.retrofit.response.ResponseLogin
import com.example.msk2.retrofit.response.ResponseRegistro

class AuthViewModel : ViewModel(){
    var responseLogin: LiveData<ResponseLogin>
    var responseRegistro : LiveData<ResponseRegistro>
    private var authRepository = AuthRepository()

    init {
        responseLogin = authRepository.loginResponse
        responseRegistro = authRepository.registroResponse
    }

    fun autenticarEstiba(nrodocumento:String, contrasenia:String){
        responseLogin = authRepository.autenticarEstiba(RequestLogin(nrodocumento,contrasenia))
    }

    fun registrarEstiba(documento:Boolean,
                        nrodocumento: String,
                        contrasenia:String,
                        nombre:String,
                        apellido:String,
                        edad:Int,
                        telefono:String){
        responseRegistro = authRepository.registrarEstiba(
            RequestRegistro(documento, nrodocumento, contrasenia, nombre, apellido, edad,telefono)
        )
    }

}