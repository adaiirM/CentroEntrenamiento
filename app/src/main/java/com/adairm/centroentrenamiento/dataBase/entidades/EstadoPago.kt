package com.adairm.centroentrenamiento.dataBase.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estado_pago")

data class EstadoPago(@ColumnInfo(name = "id_estado_pago") @PrimaryKey(autoGenerate = true) val idEstadoPago: Int){

    @ColumnInfo(name = "nombre_estado_pago") lateinit var nombreEstadoPago: String

    constructor(
        nombreEstadoPago: String

    ) : this(0) {
        this.nombreEstadoPago = nombreEstadoPago
    }

    constructor() : this(0) {
    }
}