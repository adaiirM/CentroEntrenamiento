package com.adairm.centroentrenamiento.mvp.presenter

import android.content.Context
import com.adairm.centroentrenamiento.dataBase.entidades.ServicioDetalles
import com.adairm.centroentrenamiento.mvp.interactor.InteractorFormClienteImpl
import com.adairm.centroentrenamiento.mvp.interactor.InteractorVerServiciosImpl
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IFormClienteInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IVerServiciosInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterFormCliente
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterVerServicios
import com.adairm.centroentrenamiento.mvp.interfaces.views.IFormClienteActivity
import com.adairm.centroentrenamiento.mvp.interfaces.views.IVerServiciosActivity

class PresenterVerServiciosActivity (view: IVerServiciosActivity, context: Context):
    IPresenterVerServicios {
    private val view: IVerServiciosActivity
    private val interactor: IVerServiciosInteractor

    init {
        interactor = InteractorVerServiciosImpl(this, context)
        this.view = view
    }

    override suspend fun obtenerServicios() {
        interactor.obtenerServicios()
    }

    override suspend fun devolverServicios(servicios: List<ServicioDetalles>) {
        view.devolverServicios(servicios)
    }
}