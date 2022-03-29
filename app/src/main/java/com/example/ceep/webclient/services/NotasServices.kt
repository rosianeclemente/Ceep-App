package com.example.ceep.webclient.services

import com.example.ceep.webclient.modelResposta.NotaRequisicao
import com.example.ceep.webclient.modelResposta.NotaResposta
import retrofit2.Response
import retrofit2.http.*

interface NotasServices {


    @GET("notas")
    suspend fun buscarTodas(): List<NotaResposta>

    @PUT("notas/{id}")
    suspend fun salva(
        @Path("id") id: String,
        @Body nota: NotaRequisicao
    ): Response<NotaResposta>

    @DELETE("notas/{id}")
    suspend fun remove(@Path("id") id: String): Response<Void>
}