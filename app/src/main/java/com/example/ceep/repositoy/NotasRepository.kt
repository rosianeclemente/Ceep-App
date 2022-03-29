package com.example.ceep.repositoy

import com.example.ceep.database.dao.NotaDao
import com.example.ceep.model.Nota
import com.example.ceep.webclient.NotaWebClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first


class NotasRepository(private val dao: NotaDao, private val webClient: NotaWebClient) {

    fun buscaTodas(): Flow<List<Nota>> {
        return dao.buscaTodas()
    }

    private suspend fun atualizaTodas() {
        webClient.buscaTodas()?.let { notas ->
            val notasSincronizadas = notas.map { nota ->
                nota.copy(sincronizada = true)
            }
            dao.salvaLista(notasSincronizadas)

        }
    }

    fun buscarPorId(id: String): Flow<Nota> {
        return dao.buscaPorId(id)
    }

    suspend fun remove(id: String) {
        dao.desativa(id)
        if(webClient.remove(id)){
            dao.remove(id)
        }

    }

    suspend fun salva(nota: Nota) {
        dao.salva(nota)
        if (webClient.salva(nota)){
            val notaSincronizada = nota.copy(sincronizada = true)
            dao.salva(notaSincronizada)
        }
    }
    suspend fun sincroniza (){
        val notasDesativadas = dao.buscaDesativadas().first()
        notasDesativadas.forEach{ notaDesativada ->
            remove(notaDesativada.id)
        }
        val notasNaoSincronizada = dao.buscaNaoSincronizada().first()
        notasNaoSincronizada.forEach{ notaNaoSincronizada ->
            salva(notaNaoSincronizada)

        }
        atualizaTodas()
    }
}