package com.adairm.centroentrenamiento.mvp.interfaces.views

import com.adairm.centroentrenamiento.dataBase.entidades.Cliente

interface IOpcionesClienteActivity {
    suspend fun devolverCliente(cliente: Cliente)
}