package com.adairm.centroentrenamiento.mvp.interactor

import android.content.Context
import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.dataBase.repository.GestiosPagosRepository
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IOpcionesClienteInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterOpcionesCliente

class InteractorOpcionesClienteImpl (presenter: IPresenterOpcionesCliente, context: Context) : IOpcionesClienteInteractor {
    private val presenter: IPresenterOpcionesCliente
    private var db: GestiosPagosRepository

    init {
        this.presenter = presenter
        GestiosPagosRepository.inicializar(context)
        db = GestiosPagosRepository.get()
    }

    override suspend fun buscarPorId(id: Int) {
        presenter.devolverCliente(db.buscarPorId(id))
    }

    override suspend fun eliminarCliente(cliente: Cliente) {
        db.eliminarCliente(cliente)
    }

    override suspend fun actualizarCliente(cliente: Cliente) {
        db.actualizarCliente(cliente)
    }
}