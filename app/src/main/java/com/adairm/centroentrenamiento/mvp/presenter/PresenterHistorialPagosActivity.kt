package com.adairm.centroentrenamiento.mvp.presenter

import android.content.Context
import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.dataBase.entidades.PrestamoServiciosDetalles
import com.adairm.centroentrenamiento.mvp.interactor.InteractorFormClienteImpl
import com.adairm.centroentrenamiento.mvp.interactor.InteractorHistorialPagosImpl
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IFormClienteInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IHistorialPagosInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterFormCliente
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterHistorialPagos
import com.adairm.centroentrenamiento.mvp.interfaces.views.IFormClienteActivity
import com.adairm.centroentrenamiento.mvp.interfaces.views.IHistorialPagosActivity

class PresenterHistorialPagosActivity (view: IHistorialPagosActivity, context: Context):
    IPresenterHistorialPagos {
    private val view: IHistorialPagosActivity
    private val interactor: IHistorialPagosInteractor

    init {
        interactor = InteractorHistorialPagosImpl(this, context)
        this.view = view
    }

    override suspend fun buscarNombreConceptoPrestamo(nombreC: String, posicion: Int, idCliente: Int){
        interactor.buscarNombreConceptoPrestamo(nombreC, posicion, idCliente)
    }

    override suspend fun devolverPrestamos(prestamos: List<PrestamoServiciosDetalles>) {
        view.devolverPrestamos(prestamos)
    }

    override suspend fun obtenerConceptos() {
        interactor.obtenerConceptos()
    }

    override suspend fun devolverConceptos(conceptos: List<String>) {
        view.devolverConceptos(conceptos)
    }

    override suspend fun buscarPorNombre(nombreC: String) {
        interactor.buscarPorNombre(nombreC)
    }

    override suspend fun devolverClientes(clientes: List<Cliente>) {
        view.devolverClientes(clientes)
    }
}