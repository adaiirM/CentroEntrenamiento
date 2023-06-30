package com.adairm.centroentrenamiento.mvp.interactor

import android.content.Context
import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.dataBase.entidades.PrestamoServicios
import com.adairm.centroentrenamiento.dataBase.repository.GestiosPagosRepository
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IFormPagoNuevoInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterFormPagoNuevo

class InteractorFormPagoImpl (presenter: IPresenterFormPagoNuevo, context: Context) : IFormPagoNuevoInteractor {
    private val presenter: IPresenterFormPagoNuevo
    private var db: GestiosPagosRepository

    init {
        this.presenter = presenter
        GestiosPagosRepository.inicializar(context)
        db = GestiosPagosRepository.get()
    }

    override suspend fun actualizarCliente(cliente: Cliente) {
        db.actualizarCliente(cliente)
    }

    override suspend fun insertarPrestamo(prestamoServicios: PrestamoServicios) {
        db.insertarPrestamo(prestamoServicios)
    }

    override suspend fun buscarPorId(id: Int) {
        presenter.devolverCliente(db.buscarPorId(id))
    }

    override suspend fun obtenerClientes() {
        presenter.devolverClientes(db.obtenerClientes())
    }

    override suspend fun obtenerServicios() {
        presenter.devolverServicios(db.obtenerServicios())
    }

    override suspend fun obtenerEstadoPago() {
        presenter.devolverEstadoPago(db.obtenerEstadoPago())
    }

    override suspend fun buscarPorNombre(nombreC: String) {
        presenter.devolverClientes(db.buscarPorNombre(nombreC))
    }
}