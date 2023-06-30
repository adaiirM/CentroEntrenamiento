package com.adairm.centroentrenamiento.mvp.interfaces.interactors

import com.adairm.centroentrenamiento.dataBase.entidades.Servicios

interface IFormServicioNuevoInteractor {
    suspend fun insertarServicio(servicios: Servicios)
    suspend fun obtenerConceptos()


}