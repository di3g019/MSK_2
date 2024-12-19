package com.example.msk2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.msk2.db.MSKDataBase
import com.example.msk2.db.entity.EstibaEntity
import com.example.msk2.repository.EstibaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EstibaViewModel(application: Application):AndroidViewModel(application) {

    private val repository : EstibaRepository
    init {
        val estibaDao = MSKDataBase.getDatabase(application).estibaDao()
        repository = EstibaRepository(estibaDao)
    }

    fun insertar(estibaEntity: EstibaEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertar(estibaEntity)
    }

    fun actualizar(estibaEntity: EstibaEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.actualizar(estibaEntity)
    }

    fun eliminar() = viewModelScope.launch(Dispatchers.IO) {
        repository.eliminar()
    }

    fun obtener():LiveData<EstibaEntity>{
        return repository.obtener()
    }

    fun obtenerNroDocumento():LiveData<String>{
        return repository.obtenerNroDocumento()
    }

}