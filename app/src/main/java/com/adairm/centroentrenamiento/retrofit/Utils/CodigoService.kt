package com.adairm.ejercicio3er.Utils

import com.adairm.centroentrenamiento.retrofit.Codigo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CodigoService {
    @GET("list")
    fun getCodigos(): Call<List<Codigo>>

    @POST("add")
    fun addCodigo(@Body codigo: Codigo): Call<Codigo>

    @POST("update/id")
    fun updateCodigo(@Body codigo: Codigo, @Path("id") id: Int): Call<Codigo>

    @POST("delete/{id}")
    fun deleteCodigo(@Path("id") id: Int): Call<Codigo>

    @POST("listacodigo/{codigo}")
    fun getByCodigo(@Path("codigo") cp: String): Call<List<Codigo>>
}