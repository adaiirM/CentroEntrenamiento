package com.adairm.centroentrenamiento.dataBase.entidades

import androidx.room.Embedded
import androidx.room.Relation

data class ServicioDetalles(
    @Embedded val servicios: Servicios,
    @Relation(
        parentColumn = "id_concepto_cobro",
        entityColumn = "id_concepto_cobro"
    )
    val conceptoCobro: ConceptoCobro)
