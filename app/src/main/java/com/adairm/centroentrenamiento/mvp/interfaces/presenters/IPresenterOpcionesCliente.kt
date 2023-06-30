package com.adairm.centroentrenamiento.mvp.interfaces.presenters

import com.adairm.centroentrenamiento.dataBase.entidades.Cliente

interface IPresenterOpcionesCliente {
    suspend fun buscarPorId(id: Int)
    suspend fun devolverCliente(cliente: Cliente)

    suspend fun eliminarCliente(cliente: Cliente)
    suspend fun actualizarCliente(cliente: Cliente)

}