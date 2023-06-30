package com.adairm.centroentrenamiento.mvp.interfaces.views

interface IFormServicioNuevoActivity {
    suspend fun devolverConceptos(conceptos: List<String>)
}