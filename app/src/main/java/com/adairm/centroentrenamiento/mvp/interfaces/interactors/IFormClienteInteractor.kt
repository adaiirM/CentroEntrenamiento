package com.adairm.centroentrenamiento.mvp.interfaces.interactors

import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.retrofit.Codigo

interface IFormClienteInteractor {
    suspend fun insertarCliente(cliente: Cliente)
}