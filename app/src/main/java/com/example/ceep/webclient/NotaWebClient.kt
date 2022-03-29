package com.example.ceep.webclient

import android.util.Log
import com.example.ceep.model.Nota
import com.example.ceep.webclient.services.NotasServices
import java.lang.Exception

private const val TAG = "NotaWebClient"
class NotaWebClient {

    private val notasServices: NotasServices = RetrofitInicializador().notasServices
    suspend fun buscaTodas(): List<Nota>? {
        //tratamenta para off-line
        return try {
            val notasResposta = RetrofitInicializador().notasServices
                .buscarTodas()
            return notasResposta.map { notaResposta ->
                notaResposta.nota
            }
        }catch (e: Exception){
            Log.e(TAG, "buscarTodas: ", e)
            null
        }
    }
}