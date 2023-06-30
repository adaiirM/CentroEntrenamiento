package com.adairm.centroentrenamiento.mvp.interfaces.presenters

import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.dataBase.entidades.PrestamoServicios
import com.adairm.centroentrenamiento.dataBase.entidades.ServicioDetalles

interface IPresenterFormPagoNuevo {
    suspend fun actualizarCliente(cliente: Cliente)
    suspend fun insertarPrestamo(prestamoServicios: PrestamoServicios)
    suspend fun buscarPorId(id: Int)
    suspend fun obtenerClientes()
    suspend fun obtenerServicios()
    suspend fun obtenerEstadoPago()
    suspend fun buscarPorNombre(nombreC: String)

    suspend fun devolverCliente(cliente: Cliente)
    suspend fun devolverClientes(clientes: List<Cliente>)
    suspend fun devolverServicios(servicios: List<ServicioDetalles>)
    suspend fun devolverEstadoPago(list: List<String>)
}