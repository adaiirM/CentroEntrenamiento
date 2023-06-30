package com.adairm.centroentrenamiento.dataBase.entidades

import androidx.room.Embedded
import androidx.room.Relation

data class PrestamoServiciosDetalles(
    @Embedded val prestamoServicios: PrestamoServicios,
    @Relation(
        parentColumn = "id_servicio",
        entityColumn = "id_servicio"
    )
    val servicios: Servicios,
    @Relation(
        parentColumn = "id_estado_pago",
        entityColumn = "id_estado_pago"
    )
    val estadoPago: EstadoPago,
    @Relation(
        parentColumn = "id_cliente",
        entityColumn = "id_cliente"
    )
    val cliente: Cliente)
