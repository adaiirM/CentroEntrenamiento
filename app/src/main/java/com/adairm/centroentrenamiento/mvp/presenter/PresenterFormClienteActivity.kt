package com.adairm.centroentrenamiento.mvp.presenter

import android.content.Context
import com.adairm.centroentrenamiento.dataBase.entidades.Cliente
import com.adairm.centroentrenamiento.mvp.interactor.InteractorFormClienteImpl
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IFormClienteInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterFormCliente
import com.adairm.centroentrenamiento.mvp.interfaces.views.IFormClienteActivity

class PresenterFormClienteActivity (view: IFormClienteActivity, context: Context): IPresenterFormCliente {
    private val view: IFormClienteActivity
    private val interactor: IFormClienteInteractor

    init {
        interactor = InteractorFormClienteImpl(this, context)
        this.view = view
    }

    override suspend fun insertarCliente(cliente: Cliente) {
        interactor.insertarCliente(cliente)
    }

}