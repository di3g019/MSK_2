package com.example.msk2.retrofit.response

import java.math.BigDecimal
import java.sql.Time
import java.util.Date

data class ResponseProgramacion(
    var idprogramacion:String,
    var fecha:Date,
    var lugar:String,
    var coordenada:String,
    var hora:Time,
    var tipocontenedor:String,
    var cantidadcontenedores:Int,
    var productos:String,
    var pagobase:BigDecimal,
    var estado:Boolean,
    var estiba: Estiba
){
    data class Estiba(
        var idestiba:Int,
        var documento:Boolean,
        var nrodocumento:String,
        var contrasenia:String,
        var nombre:String,
        var apellido:String,
        var edad:Int,
        var telefono:String
    )
}
