package com.example.msk2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.msk2.repository.AuthRepository
import com.example.msk2.repository.ProgramacionRepository
import com.example.msk2.retrofit.response.ResponseLogin
import com.example.msk2.retrofit.response.ResponseProgramacion

class ProgramacionesViewModel : ViewModel(){
    private var repository = ProgramacionRepository()

    fun cargarProgramaciones(nrodocumento:String):LiveData<List<ResponseProgramacion>>{
        return repository.listarProgramaciones(nrodocumento)
    }

}