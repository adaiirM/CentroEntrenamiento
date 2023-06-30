package com.adairm.centroentrenamiento.mvp.interactor

import android.content.Context
import com.adairm.centroentrenamiento.dataBase.entidades.Servicios
import com.adairm.centroentrenamiento.dataBase.repository.GestiosPagosRepository
import com.adairm.centroentrenamiento.mvp.interfaces.interactors.IFormServicioNuevoInteractor
import com.adairm.centroentrenamiento.mvp.interfaces.presenters.IPresenterFormServicioNuevo

class InteractorFormServicioImpl (presenter: IPresenterFormServicioNuevo, context: Context) : IFormServicioNuevoInteractor {
    private val presenter: IPresenterFormServicioNuevo
    private var db: GestiosPagosRepository

    init {
        this.presenter = presenter
        GestiosPagosRepository.inicializar(context)
        db = GestiosPagosRepository.get()
    }

    override suspend fun insertarServicio(servicios: Servicios) {
        db.insertarServicio(servicios)
    }

    override suspend fun obtenerConceptos() {
        presenter.devolverConceptos(db.obtenerConceptos())
    }
}