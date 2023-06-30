package com.adairm.centroentrenamiento.mvp.interfaces.views

import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.dataBase.entidades.ServicioDetalles

interface IFormPagoNuevoActivity {
    suspend fun devolverCliente(cliente: Cliente)
    suspend fun devolverClientes(clientes: List<Cliente>)
    suspend fun devolverServicios(servicios: List<ServicioDetalles>)
    suspend fun devolverEstadoPago(lista: List<String>)
}