package com.adairm.centroentrenamiento.dataBase.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.dataBase.entidades.ConceptoCobro

@Dao
interface ConceptoCobroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(conceptoCobro: ConceptoCobro)

    @Query("SELECT nombre_concepto_cobro FROM concepto_cobro")
    suspend fun getAll(): List<String>
}