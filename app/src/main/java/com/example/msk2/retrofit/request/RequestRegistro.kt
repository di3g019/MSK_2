package com.example.msk2.retrofit.request

data class RequestRegistro(
    var documento:Boolean,
    var nrodocumento:String,
    var contrasenia:String,
    var nombre:String,
    var apellido:String,
    var edad:Int,
    var telefono:String
)
