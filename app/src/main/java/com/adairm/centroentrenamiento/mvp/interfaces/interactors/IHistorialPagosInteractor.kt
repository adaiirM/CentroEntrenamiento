package com.adairm.centroentrenamiento.mvp.interfaces.interactors

interface IHistorialPagosInteractor {
    suspend fun buscarNombreConceptoPrestamo(nombreC: String, posicion: Int, idCliente: Int)
    suspend fun obtenerConceptos()

    suspend fun buscarPorNombre(nombreC: String)
}