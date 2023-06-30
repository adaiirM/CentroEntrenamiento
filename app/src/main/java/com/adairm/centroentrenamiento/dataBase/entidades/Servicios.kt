package com.adairm.centroentrenamiento.dataBase.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "servicios", foreignKeys = [
    ForeignKey(
        ConceptoCobro::class,
        parentColumns = ["id_concepto_cobro"],
        childColumns = ["id_concepto_cobro"])
])

data class Servicios(@ColumnInfo(name = "id_servicio") @PrimaryKey(autoGenerate = true) val idServicio: Int){

    @ColumnInfo(name = "id_concepto_cobro") var idConceptoCobro: Int = 0
    @ColumnInfo(name = "monto_cobro") var montoCobro: Double = 0.0
    @ColumnInfo(name = "descripcion_cobro") lateinit var descripcionCobro: String

    constructor(
        idConceptoCobro: Int,
        montoCobro: Double,
        descripcionCobro: String

    ) : this(0) {
        this.idConceptoCobro = idConceptoCobro
        this.montoCobro = montoCobro
        this.descripcionCobro = descripcionCobro
    }

    constructor(
        idServicio: Int,
        idConceptoCobro: Int,
        montoCobro: Double,
        descripcionCobro: String

    ) : this(idServicio) {
        this.idConceptoCobro = idConceptoCobro
        this.montoCobro = montoCobro
        this.descripcionCobro = descripcionCobro
    }

    constructor() : this(0) {
    }
}
