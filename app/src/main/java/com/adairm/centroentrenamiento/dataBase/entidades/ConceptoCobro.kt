package com.adairm.centroentrenamiento.dataBase.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "concepto_cobro")

data class ConceptoCobro(@ColumnInfo(name = "id_concepto_cobro") @PrimaryKey(autoGenerate = true) val idConceptoCobro: Int){

    @ColumnInfo(name = "nombre_concepto_cobro") lateinit var nombreConceptoCobro: String

    constructor(
        nombreConceptoCobro: String

    ) : this(0) {
        this.nombreConceptoCobro = nombreConceptoCobro
    }

    constructor() : this(0) {
    }
}