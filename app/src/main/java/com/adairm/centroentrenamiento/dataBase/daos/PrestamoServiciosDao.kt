package com.adairm.centroentrenamiento.dataBase.daos

import androidx.room.*
import com.adairm.centroentrenamiento.dataBase.entidades.PrestamoServicios
import com.adairm.centroentrenamiento.dataBase.entidades.PrestamoServiciosDetalles

@Dao
interface PrestamoServiciosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(prestamoServicios: PrestamoServicios)

    @Query("SELECT * FROM prestamo_servicios")
    suspend fun getAll(): List<PrestamoServicios>

    @Query("SELECT * FROM prestamo_servicios WHERE id_prestamo_servicio LIKE :id")
    suspend fun findById(id: Int): PrestamoServicios

    @Update
    suspend fun update(prestamoServicios: PrestamoServicios)

    @Transaction
    @Query("SELECT * FROM prestamo_servicios INNER JOIN servicios ON prestamo_servicios.id_servicio = servicios.id_servicio " +
            "INNER JOIN clientes ON prestamo_servicios.id_cliente = clientes.id_cliente " +
            "INNER JOIN estado_pago ON prestamo_servicios.id_estado_pago = estado_pago.id_estado_pago " +
            "WHERE prestamo_servicios.id_estado_pago = 1 AND servicios.id_concepto_cobro LIKE :id")
    suspend fun getPrestamosServicioPendientes(id: Int): List<PrestamoServiciosDetalles>

    @Transaction
    @Query("SELECT * FROM prestamo_servicios INNER JOIN servicios ON prestamo_servicios.id_servicio = servicios.id_servicio " +
            "INNER JOIN clientes ON prestamo_servicios.id_cliente = clientes.id_cliente " +
            "INNER JOIN estado_pago ON prestamo_servicios.id_estado_pago = estado_pago.id_estado_pago " +
            "WHERE prestamo_servicios.id_prestamo_servicio LIKE :id")
    suspend fun getPrestamosServiciobyId(id: Int): PrestamoServiciosDetalles

    @Transaction
    @Query("SELECT * FROM prestamo_servicios INNER JOIN servicios ON prestamo_servicios.id_servicio = servicios.id_servicio " +
            "INNER JOIN clientes ON prestamo_servicios.id_cliente = clientes.id_cliente " +
            "INNER JOIN estado_pago ON prestamo_servicios.id_estado_pago = estado_pago.id_estado_pago " +
            "WHERE clientes.nombre_completo LIKE :nombreC AND servicios.id_concepto_cobro LIKE :id " +
            "AND clientes.id_cliente LIKE :idCliente")
    suspend fun getPrestamosServicioByNombreConcepto(nombreC:String, id: Int, idCliente: Int): List<PrestamoServiciosDetalles>
}