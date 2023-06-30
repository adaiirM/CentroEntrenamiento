package com.adairm.centroentrenamiento.mvp.interactor

import android.content.Context
import com.adairm.centroentrenamiento.dataBase.repository.GestiosPagosRepository
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IVerClientesInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterVerClientes

class InteractorVerClientesImpl (presenter: IPresenterVerClientes, context: Context) : IVerClientesInteractor {
    private val presenter: IPresenterVerClientes
    private var db: GestiosPagosRepository

    init {
        this.presenter = presenter
        GestiosPagosRepository.inicializar(context)
        db = GestiosPagosRepository.get()
    }

    override suspend fun obtenerClientes() {
        presenter.devolverClientes(db.obtenerClientes())
    }

    override suspend fun buscarPorNombre(nombreC: String) {
        presenter.devolverClientes(db.buscarPorNombre(nombreC))
    }
}