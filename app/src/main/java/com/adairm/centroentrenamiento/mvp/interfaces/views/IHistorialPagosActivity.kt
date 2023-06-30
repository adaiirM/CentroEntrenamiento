package com.adairm.centroentrenamiento.mvp.interfaces.views

import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.dataBase.entidades.PrestamoServiciosDetalles

interface IHistorialPagosActivity {
    suspend fun devolverPrestamos(prestamos: List<PrestamoServiciosDetalles>)
    suspend fun devolverConceptos(conceptos: List<String>)

    suspend fun devolverClientes(clientes: List<Cliente>)

}