package com.example.msk2.retrofit.response

data class ResponseLogin(
    var success:Boolean,
    var idestiba:Int?,
    var documento:Boolean?,
    var nrodocumento:String?,
    var nombre:String?,
    var apellido:String?,
    var edad:Int?,
    var telefono:String?,
    var mensaje:String
)
