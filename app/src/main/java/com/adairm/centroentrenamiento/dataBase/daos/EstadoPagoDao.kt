package com.adairm.centroentrenamiento.dataBase.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adairm.centroentrenamiento.dataBase.entidades.EstadoPago

@Dao
interface EstadoPagoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(estadoPago: EstadoPago)

    @Query("SELECT nombre_estado_pago FROM estado_pago")
    suspend fun getAll(): List<String>
}