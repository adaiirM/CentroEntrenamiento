package com.adairm.centroentrenamiento.mvp.interactor

import android.content.Context
import com.adairm.centroentrenamiento.dataBase.repository.GestiosPagosRepository
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IHistorialPagosInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterHistorialPagos

class InteractorHistorialPagosImpl (presenter: IPresenterHistorialPagos, context: Context) : IHistorialPagosInteractor {
    private val presenter: IPresenterHistorialPagos
    private var db: GestiosPagosRepository

    init {
        this.presenter = presenter
        GestiosPagosRepository.inicializar(context)
        db = GestiosPagosRepository.get()
    }

    override suspend fun buscarNombreConceptoPrestamo(nombreC: String, posicion: Int, idCliente: Int) {
        presenter.devolverPrestamos(db.buscarNombreConceptoPrestamo(nombreC, posicion, idCliente))
    }

    override suspend fun obtenerConceptos() {
        presenter.devolverConceptos(db.obtenerConceptos())
    }

    override suspend fun buscarPorNombre(nombreC: String) {
        presenter.devolverClientes(db.buscarPorNombre(nombreC))
    }
}