package com.example.ceep.webclient.services

import com.example.ceep.model.Nota
import com.example.ceep.webclient.modelResposta.NotaResposta
import retrofit2.Call
import retrofit2.http.GET

interface NotasServices {

    @GET("notas")
    fun buscarTodos(): Call<List<NotaResposta>>
}