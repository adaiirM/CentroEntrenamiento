package com.adairm.centroentrenamiento.mvp.presenter

import android.content.Context
import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.mvp.interactor.InteractorFormClienteImpl
import com.adairm.centroentrenamiento.mvp.interactor.InteractorOpcionesClienteImpl
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IFormClienteInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IOpcionesClienteInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterFormCliente
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterOpcionesCliente
import com.adairm.centroentrenamiento.mvp.interfaces.views.IFormClienteActivity
import com.adairm.centroentrenamiento.mvp.interfaces.views.IOpcionesClienteActivity

class PresenterOpcionesClienteActivity (view: IOpcionesClienteActivity, context: Context):
    IPresenterOpcionesCliente {
    private val view: IOpcionesClienteActivity
    private val interactor: IOpcionesClienteInteractor

    init {
        interactor = InteractorOpcionesClienteImpl(this, context)
        this.view = view
    }

    override suspend fun buscarPorId(id: Int) {
        interactor.buscarPorId(id)
    }

    override suspend fun devolverCliente(cliente: Cliente) {
        view.devolverCliente(cliente)
    }

    override suspend fun eliminarCliente(cliente: Cliente) {
        interactor.eliminarCliente(cliente)
    }

    override suspend fun actualizarCliente(cliente: Cliente) {
        interactor.actualizarCliente(cliente)
    }
}