package com.adairm.centroentrenamiento.dataBase.daos

import androidx.room.*
import com.adairm.centroentrenamiento.dataBase.entidades.Cliente

@Dao
interface ClienteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cliente: Cliente)

    @Query("SELECT * FROM clientes")
    suspend fun getAll(): List<Cliente>

    @Query("SELECT * FROM clientes WHERE id_cliente LIKE :id")
    suspend fun findById(id: Int): Cliente

    @Delete
    suspend fun delete(cliente: Cliente)

    @Update
    suspend fun update(cliente: Cliente)

    @Query("SELECT * FROM clientes WHERE nombre_completo LIKE :nombreCompleto")
    suspend fun findByName(nombreCompleto: String): List<Cliente>
}