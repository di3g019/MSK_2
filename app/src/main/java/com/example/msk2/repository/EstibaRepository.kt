package com.example.msk2.repository

import androidx.lifecycle.LiveData
import com.example.msk2.db.dao.EstibaDao
import com.example.msk2.db.entity.EstibaEntity

class EstibaRepository(private val estibaDao : EstibaDao) {
    suspend fun insertar(estibaEntity: EstibaEntity){
        estibaDao.insertar(estibaEntity)
    }
    suspend fun actualizar(estibaEntity: EstibaEntity){
        estibaDao.actualizar(estibaEntity)
    }
    suspend fun eliminar(){
        estibaDao.eliminar()
    }

    fun obtener():LiveData<EstibaEntity>{
        return estibaDao.obtener()
    }

    fun obtenerNroDocumento(): LiveData<String>{
        return estibaDao.obtenerNroDocumento()
    }
}