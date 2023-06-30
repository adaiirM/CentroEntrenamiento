package com.adairm.centroentrenamiento.mvp.interfaces.interactors

import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.dataBase.entidades.PrestamoServicios

interface IFormPagoNuevoInteractor {
    suspend fun actualizarCliente(cliente: Cliente)
    suspend fun insertarPrestamo(prestamoServicios: PrestamoServicios)
    suspend fun buscarPorId(id: Int)
    suspend fun obtenerClientes()
    suspend fun obtenerServicios()
    suspend fun obtenerEstadoPago()
    suspend fun buscarPorNombre(nombreC: String)
}