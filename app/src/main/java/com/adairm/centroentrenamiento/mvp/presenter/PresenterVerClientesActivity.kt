package com.adairm.centroentrenamiento.mvp.presenter

import android.content.Context
import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.mvp.interactor.InteractorFormClienteImpl
import com.adairm.centroentrenamiento.mvp.interactor.InteractorVerClientesImpl
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IFormClienteInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IVerClientesInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterFormCliente
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterVerClientes
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterVerServicios
import com.adairm.centroentrenamiento.mvp.interfaces.views.IFormClienteActivity
import com.adairm.centroentrenamiento.mvp.interfaces.views.IVerClientesActivity

class PresenterVerClientesActivity (view: IVerClientesActivity, context: Context):
    IPresenterVerClientes {
    private val view: IVerClientesActivity
    private val interactor: IVerClientesInteractor

    init {
        interactor = InteractorVerClientesImpl(this, context)
        this.view = view
    }

    override suspend fun obtenerClientes() {
        interactor.obtenerClientes()
    }

    override suspend fun devolverClientes(clientes: List<Cliente>) {
        view.devolverClientes(clientes)
    }

    override suspend fun buscarPorNombre(nombreC: String) {
        interactor.buscarPorNombre(nombreC)
    }
}