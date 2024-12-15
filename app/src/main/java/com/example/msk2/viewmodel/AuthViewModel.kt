package com.example.msk2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.msk2.repository.AuthRepository
import com.example.msk2.retrofit.request.RequestLogin
import com.example.msk2.retrofit.response.ResponseLogin

class AuthViewModel : ViewModel(){
    var responseLogin: LiveData<ResponseLogin>
    private var authRepository = AuthRepository()

    init {
        responseLogin = authRepository.loginResponse
    }

    fun autenticarEstiba(nrodocumento:String, contrasenia:String){
        responseLogin = authRepository.autenticarEstiba(RequestLogin(nrodocumento,contrasenia))
    }

}