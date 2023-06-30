package com.adairm.centroentrenamiento.mvp.interfaces.presenters

import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.dataBase.entidades.PrestamoServiciosDetalles

interface IPresenterHistorialPagos {
    suspend fun buscarNombreConceptoPrestamo(nombreC: String, posicion: Int, idCliente: Int)
    suspend fun devolverPrestamos(prestamos: List<PrestamoServiciosDetalles>)

    suspend fun obtenerConceptos()
    suspend fun devolverConceptos(conceptos: List<String>)

    suspend fun buscarPorNombre(nombreC: String)
    suspend fun devolverClientes(clientes: List<Cliente>)
}