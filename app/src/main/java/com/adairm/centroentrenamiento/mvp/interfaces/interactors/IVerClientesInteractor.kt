package com.adairm.centroentrenamiento.mvp.interfaces.interactors

interface IVerClientesInteractor {
    suspend fun obtenerClientes()

    suspend fun buscarPorNombre(nombreC: String)
}