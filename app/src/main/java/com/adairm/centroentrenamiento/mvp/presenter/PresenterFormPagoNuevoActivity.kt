package com.adairm.centroentrenamiento.mvp.presenter

import android.content.Context
import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.dataBase.entidades.PrestamoServicios
import com.adairm.centroentrenamiento.dataBase.entidades.ServicioDetalles
import com.adairm.centroentrenamiento.mvp.interactor.InteractorFormPagoImpl
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IFormPagoNuevoInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterFormPagoNuevo
import com.adairm.centroentrenamiento.mvp.interfaces.views.IFormPagoNuevoActivity

class PresenterFormPagoNuevoActivity (view: IFormPagoNuevoActivity, context: Context):
    IPresenterFormPagoNuevo {
    private val view: IFormPagoNuevoActivity
    private val interactor: IFormPagoNuevoInteractor

    init {
        interactor = InteractorFormPagoImpl(this, context)
        this.view = view
    }

    override suspend fun actualizarCliente(cliente: Cliente) {
        interactor.actualizarCliente(cliente)
    }

    override suspend fun insertarPrestamo(prestamoServicios: PrestamoServicios) {
        interactor.insertarPrestamo(prestamoServicios)
    }

    override suspend fun buscarPorId(id: Int) {
        interactor.buscarPorId(id)
    }

    override suspend fun obtenerClientes() {
        interactor.obtenerClientes()
    }

    override suspend fun obtenerServicios() {
        interactor.obtenerServicios()
    }

    override suspend fun obtenerEstadoPago() {
        interactor.obtenerEstadoPago()
    }

    override suspend fun buscarPorNombre(nombreC: String) {
        interactor.buscarPorNombre(nombreC)
    }

    override suspend fun devolverCliente(cliente: Cliente) {
        view.devolverCliente(cliente)
    }

    override suspend fun devolverClientes(clientes: List<Cliente>) {
        view.devolverClientes(clientes)
    }

    override suspend fun devolverServicios(servicios: List<ServicioDetalles>) {
        view.devolverServicios(servicios)
    }

    override suspend fun devolverEstadoPago(list: List<String>) {
        view.devolverEstadoPago(list)
    }
}
