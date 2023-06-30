package com.adairm.centroentrenamiento.mvp.interfaces.presenters

import com.adairm.centroentrenamiento.dataBase.entidades.ServicioDetalles
import com.adairm.centroentrenamiento.dataBase.entidades.Servicios

interface IPresenterVerServicios {
    suspend fun obtenerServicios()
    suspend fun devolverServicios(servicios: List<ServicioDetalles>)

}