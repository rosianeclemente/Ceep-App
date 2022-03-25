package com.example.ceep.webclient

import com.example.ceep.webclient.services.NotasServices
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInicializador {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://172.17.0.1:8080/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    val notasServices = retrofit.create(NotasServices::class.java)
}