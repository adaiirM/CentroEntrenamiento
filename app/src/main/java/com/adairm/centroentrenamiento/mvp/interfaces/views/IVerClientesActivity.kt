package com.adairm.centroentrenamiento.mvp.interfaces.views

import com.adairm.centroentrenamiento.dataBase.entidades.Cliente

interface IVerClientesActivity {
    suspend fun devolverClientes(clientes: List<Cliente>)

}