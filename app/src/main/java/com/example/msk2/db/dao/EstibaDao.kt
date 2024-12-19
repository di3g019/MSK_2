package com.example.msk2.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.msk2.db.entity.EstibaEntity

@Dao
interface EstibaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertar(vararg estia:EstibaEntity)

    @Update
    fun actualizar(vararg estiba:EstibaEntity)

    @Query("DELETE FROM estiba")
    fun eliminar()

    @Query("SELECT * FROM estiba LIMIT 1")
    fun obtener():LiveData<EstibaEntity>

    @Query("SELECT nrodocumento FROM estiba LIMIT 1")
    fun obtenerNroDocumento():LiveData<String>
}