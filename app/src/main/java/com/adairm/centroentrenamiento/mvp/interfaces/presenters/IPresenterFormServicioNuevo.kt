package com.adairm.centroentrenamiento.mvp.interfaces.presenters

import com.adairm.centroentrenamiento.dataBase.entidades.Servicios

interface IPresenterFormServicioNuevo {
    suspend fun insertarServicio(servicios: Servicios)

    suspend fun obtenerConceptos()
    suspend fun devolverConceptos(conceptos: List<String>)
}