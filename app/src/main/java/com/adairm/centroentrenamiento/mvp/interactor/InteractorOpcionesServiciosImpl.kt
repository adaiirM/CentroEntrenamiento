package com.adairm.centroentrenamiento.mvp.interactor

import android.content.Context
import com.adairm.centroentrenamiento.dataBase.repository.GestiosPagosRepository
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IOpcionesServiciosInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterOpcionesCliente
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterOpcionesServicios

class InteractorOpcionesServiciosImpl (presenter: IPresenterOpcionesServicios, context: Context) : IOpcionesServiciosInteractor {
    private val presenter: IPresenterOpcionesServicios
    private var db: GestiosPagosRepository

    init {
        this.presenter = presenter
        GestiosPagosRepository.inicializar(context)
        db = GestiosPagosRepository.get()
    }
}