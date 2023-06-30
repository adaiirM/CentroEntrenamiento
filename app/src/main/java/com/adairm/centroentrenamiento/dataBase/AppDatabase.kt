package com.adairm.centroentrenamiento.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adairm.centroentrenamiento.dataBase.daos.*
import com.adairm.centroentrenamiento.dataBase.entidades.*

//Referencia de clase

@Database(entities = [Cliente::class, Servicios::class, PrestamoServicios::class, ConceptoCobro::class,
                     EstadoPago::class], version = 1) abstract class AppDatabase: RoomDatabase() {
    abstract fun clienteDao(): ClienteDao
    abstract fun serviciosDao(): ServiciosDao
    abstract fun prestamoServiciosDao(): PrestamoServiciosDao
    abstract fun conceptoCobroDao(): ConceptoCobroDao
    abstract fun estadoPagoDao(): EstadoPagoDao

}