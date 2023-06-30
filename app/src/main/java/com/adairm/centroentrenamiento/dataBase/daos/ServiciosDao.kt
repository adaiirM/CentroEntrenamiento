package com.adairm.centroentrenamiento.dataBase.daos

import androidx.room.*
import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.dataBase.entidades.ServicioDetalles
import com.adairm.centroentrenamiento.dataBase.entidades.Servicios

@Dao
interface ServiciosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(servicio: Servicios)

    @Query("SELECT * FROM servicios WHERE id_servicio LIKE :id")
    suspend fun findById(id: Int): Servicios

    @Delete
    suspend fun delete(servicio: Servicios)

    @Update
    suspend fun update(servicio: Servicios)

    @Transaction
    @Query("SELECT * FROM servicios INNER JOIN concepto_cobro ON servicios.id_concepto_cobro = concepto_cobro.id_concepto_cobro")
    suspend fun getServicioDetalles(): List<ServicioDetalles>

}