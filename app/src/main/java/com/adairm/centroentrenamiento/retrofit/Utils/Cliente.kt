package com.adairm.ejercicio3er.Utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Cliente {

    companion object{
        fun getClient(url: String): Retrofit{
            return Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }


}