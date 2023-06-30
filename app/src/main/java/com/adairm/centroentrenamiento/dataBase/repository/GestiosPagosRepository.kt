package com.adairm.centroentrenamiento.dataBase.repository

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.adairm.centroentrenamiento.dataBase.AppDatabase
import com.adairm.centroentrenamiento.dataBase.entidades.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val DATA_BASE_NAME = "prestamo_servicos-database.db"

class GestiosPagosRepository private constructor(context: Context){

    private val roomCallback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            CoroutineScope (Dispatchers.IO).launch {
                val conceptoCobro = dbRoom.conceptoCobroDao()
                conceptoCobro.insert(ConceptoCobro("Dia"))
                conceptoCobro.insert(ConceptoCobro("Semana"))
                conceptoCobro.insert(ConceptoCobro("Mes"))

                val estadoPago = dbRoom.estadoPagoDao()
                estadoPago.insert(EstadoPago("Pendiente"))
                estadoPago.insert(EstadoPago("Pagado"))

            }
        }
    }

    private val dbRoom: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        DATA_BASE_NAME
    )
        .addCallback(roomCallback)
        .build()

    private val clienteDao = dbRoom.clienteDao()
    private val serviciosDao = dbRoom.serviciosDao()
    private val prestamoServiciosDao = dbRoom.prestamoServiciosDao()
    private val conceptoCobroDao = dbRoom.conceptoCobroDao()
    private val estadoPagoDao = dbRoom.estadoPagoDao()

    //Consultas a la tabla clientes
    suspend fun insertarCliente(cliente: Cliente) = clienteDao.insert(cliente)
    suspend fun obtenerClientes(): List<Cliente> = clienteDao.getAll()
    suspend fun buscarPorId(id: Int): Cliente = clienteDao.findById(id)
    suspend fun eliminarCliente(cliente: Cliente) = clienteDao.delete(cliente)
    suspend fun actualizarCliente(cliente: Cliente) = clienteDao.update(cliente)
    suspend fun buscarPorNombre(nombreCompleto: String): List<Cliente> = clienteDao.findByName(nombreCompleto)

    //Consultas a la tabla servicios
    suspend fun insertarServicio(servicio: Servicios) = serviciosDao.insert(servicio)
    suspend fun obtenerServicios() = serviciosDao.getServicioDetalles()
    suspend fun buscarServicioPorId(id: Int): Servicios = serviciosDao.findById(id)
    suspend fun eliminarServicio(servicio: Servicios) = serviciosDao.delete(servicio)
    suspend fun actualizarServicio(servicio: Servicios) = serviciosDao.update(servicio)

    //Consultas a la entidad prestamo de servicios
    suspend fun insertarPrestamo(prestamoServicios: PrestamoServicios) = prestamoServiciosDao.insert(prestamoServicios)
    suspend fun buscarIdPrestamo(id: Int) = prestamoServiciosDao.findById(id)
    suspend fun actualizarPrestamo(prestamoServicios: PrestamoServicios) = prestamoServiciosDao.update(prestamoServicios)
    suspend fun buscarNombreConceptoPrestamo(nombreC: String, id: Int, idCliente: Int) =
        prestamoServiciosDao.getPrestamosServicioByNombreConcepto(nombreC, id, idCliente)

    //Consultas de los prestamos con datos d emas tablas
    suspend fun buscarPrestamosPorConcepto(id: Int) = prestamoServiciosDao.getPrestamosServicioPendientes(id)
    suspend fun obtenerPrestamoId(id: Int) = prestamoServiciosDao.getPrestamosServiciobyId(id)

    suspend fun obtenerConceptos() = conceptoCobroDao.getAll()
    suspend fun obtenerEstadoPago() = estadoPagoDao.getAll()

    companion object{
        private var INSTANCE: GestiosPagosRepository? = null

        fun inicializar(context: Context){
            if(INSTANCE == null){
                INSTANCE = GestiosPagosRepository(context)
            }
        }

        fun get(): GestiosPagosRepository {
            return INSTANCE ?: throw IllegalStateException("GestiosPagosRepository debe ser inicializado")


        }
    }

}