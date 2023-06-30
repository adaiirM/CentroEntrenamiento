package com.adairm.centroentrenamiento.mvp.interactor

import android.content.Context
import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.dataBase.repository.GestiosPagosRepository
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IFormClienteInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterFormCliente

class InteractorFormClienteImpl (presenter: IPresenterFormCliente, context: Context) : IFormClienteInteractor {
    private val presenter: IPresenterFormCliente
    private var db: GestiosPagosRepository

    init {
        this.presenter = presenter
        GestiosPagosRepository.inicializar(context)
        db = GestiosPagosRepository.get()
    }

    override suspend fun insertarCliente(cliente: Cliente) {
        db.insertarCliente(cliente)
    }

}