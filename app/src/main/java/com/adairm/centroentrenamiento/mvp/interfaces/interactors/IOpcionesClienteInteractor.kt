package com.adairm.centroentrenamiento.mvp.interfaces.interactors

import com.adairm.centroentrenamiento.dataBase.entidades.Cliente

interface IOpcionesClienteInteractor {
    suspend fun buscarPorId(id: Int)

    suspend fun eliminarCliente(cliente: Cliente)
    suspend fun actualizarCliente(cliente: Cliente)
}