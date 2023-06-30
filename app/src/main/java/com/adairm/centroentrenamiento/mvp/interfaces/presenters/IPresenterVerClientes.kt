package com.adairm.centroentrenamiento.mvp.interfaces.presenters

import com.adairm.centroentrenamiento.dataBase.entidades.Cliente

interface IPresenterVerClientes {
    suspend fun obtenerClientes()

    suspend fun devolverClientes(clientes: List<Cliente>)

    suspend fun buscarPorNombre(nombreC: String)
}