package com.adairm.centroentrenamiento.dataBase.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clientes")

data class Cliente(@ColumnInfo(name = "id_cliente") @PrimaryKey(autoGenerate = true) val idCliente: Int) {

    @ColumnInfo(name = "nombre_cliente") lateinit var nombreCliente: String
    @ColumnInfo(name = "apellido_paterno") lateinit var apellidoPaterno: String
    @ColumnInfo(name = "apellido_materno") lateinit var apellidoMaterno: String
    @ColumnInfo(name = "nombre_completo") lateinit var nombreCompleto: String
    @ColumnInfo(name = "edad") lateinit var edad: String
    @ColumnInfo(name = "peso") lateinit var peso: String
    @ColumnInfo(name = "estatura") lateinit var estatura: String
    @ColumnInfo(name = "padecimiento_medico") lateinit var padecimientoMedico: String
    @ColumnInfo(name = "consumo_medicamento") lateinit var consumoMedicamento: String
    @ColumnInfo(name = "presion_arterial") lateinit var presionArterial: String
    @ColumnInfo(name = "codigo_postal") lateinit var codigoPostal: String
    @ColumnInfo(name = "estado") lateinit var estado: String
    @ColumnInfo(name = "colonia") lateinit var colonia: String
    @ColumnInfo(name = "telefono") lateinit var telefono: String
    @ColumnInfo(name = "correo") lateinit var correo: String
    @ColumnInfo(name = "num_adeudos") var numAdeudos: Int = 0

    constructor(
        nombreCliente: String,
        apellidoPaterno: String,
        apellidoMaterno: String,
        edad: String,
        peso: String,
        estatura: String,
        padecimientoMedico: String,
        consumoMedicamento: String,
        presionArterial: String,
        codigoPostal: String,
        colonia: String,
        estado: String,
        telefono: String,
        correo: String

    ) : this(0) {
        this.nombreCliente = nombreCliente
        this.apellidoPaterno = apellidoPaterno
        this.apellidoMaterno = apellidoMaterno
        this.nombreCompleto = "$nombreCliente $apellidoPaterno $apellidoMaterno"
        this.peso = peso
        this.estatura = estatura
        this.padecimientoMedico = padecimientoMedico
        this.consumoMedicamento = consumoMedicamento
        this.presionArterial = presionArterial
        this.codigoPostal = codigoPostal
        this.colonia = colonia
        this.estado = estado
        this.edad = edad
        this.telefono = telefono
        this.correo = correo
        numAdeudos = 0
    }

    constructor(
        idCliente: Int,
        nombreCliente: String,
        apellidoPaterno: String,
        apellidoMaterno: String,
        edad: String,
        peso: String,
        estatura: String,
        padecimientoMedico: String,
        consumoMedicamento: String,
        presionArterial: String,
        codigoPostal: String,
        colonia: String,
        estado: String,
        telefono: String,
        correo: String

    ) : this(idCliente) {
        this.nombreCliente = nombreCliente
        this.apellidoPaterno = apellidoPaterno
        this.apellidoMaterno = apellidoMaterno
        this.nombreCompleto = "$nombreCliente $apellidoPaterno $apellidoMaterno"
        this.peso = peso
        this.estatura = estatura
        this.padecimientoMedico = padecimientoMedico
        this.consumoMedicamento = consumoMedicamento
        this.presionArterial = presionArterial
        this.codigoPostal = codigoPostal
        this.colonia = colonia
        this.estado = estado
        this.edad = edad
        this.telefono = telefono
        this.correo = correo
        numAdeudos = 0
    }
}
