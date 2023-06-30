package com.adairm.centroentrenamiento.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Codigo {
    @SerializedName("id")
    @Expose
    var id: Int = 0

    @SerializedName("asentamiento")
    @Expose
    var asentamiento: String

    @SerializedName("municipio")
    @Expose
    var municipio: String

    @SerializedName("estado")
    @Expose
    var estado: String

    @SerializedName("ciudad")
    @Expose
    var ciudad: String

    @SerializedName("codigo")
    @Expose
    var codigoPostal: String

    constructor(
        id: Int,
        asentamiento: String,
        municipio: String,
        estado: String,
        ciudad: String,
        codigoPostal: String
    ) {
        this.id = id
        this.asentamiento = asentamiento
        this.municipio = municipio
        this.estado = estado
        this.ciudad = ciudad
        this.codigoPostal = codigoPostal
    }
}