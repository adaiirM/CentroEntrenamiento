package com.adairm.centroentrenamiento.mvp.interactor

import android.content.Context
import com.adairm.centroentrenamiento.dataBase.repository.GestiosPagosRepository
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IVerServiciosInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterVerServicios

class InteractorVerServiciosImpl (presenter: IPresenterVerServicios, context: Context) : IVerServiciosInteractor {
    private val presenter: IPresenterVerServicios
    private var db: GestiosPagosRepository

    init {
        this.presenter = presenter
        GestiosPagosRepository.inicializar(context)
        db = GestiosPagosRepository.get()
    }

    override suspend fun obtenerServicios() {
        presenter.devolverServicios(db.obtenerServicios())
    }
}