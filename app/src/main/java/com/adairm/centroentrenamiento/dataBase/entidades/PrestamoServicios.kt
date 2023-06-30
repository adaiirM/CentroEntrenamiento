package com.adairm.centroentrenamiento.dataBase.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "prestamo_servicios", foreignKeys = [
    ForeignKey(entity = Cliente::class,
        parentColumns = ["id_cliente"],
        childColumns = ["id_cliente"]),
    ForeignKey(entity = Servicios::class,
        parentColumns = ["id_servicio"],
        childColumns = ["id_servicio"]),
    ForeignKey(entity = EstadoPago::class,
        parentColumns = ["id_estado_pago"],
        childColumns = ["id_estado_pago"])
])

data class PrestamoServicios(@ColumnInfo(name = "id_prestamo_servicio") @PrimaryKey(autoGenerate = true) val idPrestamoServicio: Int){
    @ColumnInfo(name = "fecha_inicio") lateinit var fechaInicio: String
    @ColumnInfo(name = "fecha_corte") lateinit var fechaCorte: String

    //Claves foraneas
    @ColumnInfo(name = "id_cliente") var idCliente: Int = 0
    @ColumnInfo(name = "id_servicio") var idServicio: Int = 0
    @ColumnInfo(name = "id_estado_pago") var idEstadoPago: Int = 0
    constructor(
        fechaInicio: String,
        fechaCorte: String,
        idCliente: Int,
        idServicios: Int,
        idEstadoPago: Int

    ) : this(0) {
        this.fechaInicio = fechaInicio
        this.fechaCorte = fechaCorte
        this.idCliente = idCliente
        this.idServicio = idServicios
        this.idEstadoPago = idEstadoPago
    }

    constructor(
        idPrestamoServicio: Int,
        fechaInicio: String,
        fechaCorte: String,
        idCliente: Int,
        idServicios: Int,
        idEstadoPago: Int

    ) : this(idPrestamoServicio) {
        this.fechaInicio = fechaInicio
        this.fechaCorte = fechaCorte
        this.idCliente = idCliente
        this.idServicio = idServicios
        this.idEstadoPago = idEstadoPago
    }
}