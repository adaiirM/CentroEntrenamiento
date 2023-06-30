package com.adairm.centroentrenamiento.mvp.presenter

import android.content.Context
import com.adairm.centroentrenamiento.dataBase.entidades.Servicios
import com.adairm.centroentrenamiento.mvp.interactor.InteractorFormPagoImpl
import com.adairm.centroentrenamiento.mvp.interactor.InteractorFormServicioImpl
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IFormServicioNuevoInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterFormPagoNuevo
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterFormServicioNuevo
import com.adairm.centroentrenamiento.mvp.interfaces.views.IFormPagoNuevoActivity
import com.adairm.centroentrenamiento.mvp.interfaces.views.IFormServicioNuevoActivity

class PresenterFormServicioNuevoActivity (view: IFormServicioNuevoActivity, context: Context):
    IPresenterFormServicioNuevo {
    private val view: IFormServicioNuevoActivity
    private val interactor: IFormServicioNuevoInteractor

    init {
        interactor = InteractorFormServicioImpl(this, context)
        this.view = view
    }

    override suspend fun insertarServicio(servicios: Servicios) {
        interactor.insertarServicio(servicios)
    }

    override suspend fun obtenerConceptos() {
        interactor.obtenerConceptos()
    }

    override suspend fun devolverConceptos(conceptos: List<String>) {
        view.devolverConceptos(conceptos)
    }


}