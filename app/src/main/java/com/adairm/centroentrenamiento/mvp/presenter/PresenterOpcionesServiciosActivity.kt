package com.adairm.centroentrenamiento.mvp.presenter

import android.content.Context
import com.adairm.centroentrenamiento.mvp.interactor.InteractorFormClienteImpl
import com.adairm.centroentrenamiento.mvp.interactor.InteractorOpcionesServiciosImpl
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IFormClienteInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IOpcionesServiciosInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterFormCliente
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterOpcionesServicios
import com.adairm.centroentrenamiento.mvp.interfaces.views.IFormClienteActivity
import com.adairm.centroentrenamiento.mvp.interfaces.views.IOpcionesServiciosActivity

class PresenterOpcionesServiciosActivity (view: IOpcionesServiciosActivity, context: Context):
    IPresenterOpcionesServicios {
    private val view: IOpcionesServiciosActivity
    private val interactor: IOpcionesServiciosInteractor

    init {
        interactor = InteractorOpcionesServiciosImpl(this, context)
        this.view = view
    }
}