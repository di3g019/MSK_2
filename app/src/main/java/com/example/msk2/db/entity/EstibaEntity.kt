package com.example.msk2.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "estiba")
data class EstibaEntity(
    @PrimaryKey
    val idestiba:Int?,
    val documento:Boolean?,
    val nrodocumento:String,
    val nombre:String,
    val apellido:String,
    val edad:Int?,
    val telefono:String
)
