package com.example.ceep.webclient

import android.util.Log
import com.example.ceep.model.Nota
import com.example.ceep.webclient.modelResposta.NotaRequisicao
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
    suspend fun salva(nota: Nota) : Boolean{
        try {
            val resposta = notasServices.salva(nota.id, NotaRequisicao(
                titulo = nota.titulo,
                descricao = nota.descricao,
                imagem = nota.imagem
            ))
            return resposta.isSuccessful
        }catch (e: Exception){
            Log.e(TAG, "salva: falha ao tentar salvar ", e)
        }
        return false
    }
    suspend fun remove(id: String) : Boolean{
        try{
            notasServices.remove(id)
            return true
        } catch (e: Exception){
            Log.e(TAG, "erro na remocao", e)
        }
        return false

    }
}