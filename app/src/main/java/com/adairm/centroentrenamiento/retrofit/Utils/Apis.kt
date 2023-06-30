package com.adairm.ejercicio3er.Utils

import retrofit2.create

class Apis {
    companion object{
        val URL001 = "http://192.168.0.103:8083/code/"

        fun getCodigoService(): CodigoService{
            return Cliente.getClient(URL001).create(CodigoService::class.java)
        }
    }
}