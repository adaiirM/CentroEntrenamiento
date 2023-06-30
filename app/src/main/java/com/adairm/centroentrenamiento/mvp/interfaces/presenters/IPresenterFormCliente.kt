package com.adairm.centroentrenamiento.mvp.interfaces.presenters

import com.adairm.centroentrenamiento.dataBase.entidades.Cliente

interface IPresenterFormCliente {
    suspend fun insertarCliente(cliente: Cliente)
}