package com.adairm.centroentrenamiento.mvp.interfaces.views

import com.adairm.centroentrenamiento.dataBase.entidades.ServicioDetalles

interface IVerServiciosActivity {
    suspend fun devolverServicios(servicios: List<ServicioDetalles>)

}