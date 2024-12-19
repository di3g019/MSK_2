package com.example.msk2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.msk2.db.dao.EstibaDao
import com.example.msk2.db.entity.EstibaEntity

@Database(entities = [EstibaEntity::class], version = 1)
abstract class MSKDataBase : RoomDatabase() {
    abstract  fun estibaDao(): EstibaDao

    companion object{

        @Volatile
        private var INSTANCIA : MSKDataBase? = null

        fun getDatabase(context: Context):MSKDataBase{
            val tempInstance = INSTANCIA
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MSKDataBase::class.java,
                    "mskdb"
                ).build()
                INSTANCIA = instance
                return  instance
            }
        }
    }
}