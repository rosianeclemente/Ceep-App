package com.example.ceep.repositoy

import com.example.ceep.database.dao.NotaDao
import com.example.ceep.model.Nota
import com.example.ceep.webclient.NotaWebClient
import kotlinx.coroutines.flow.Flow


class NotasRepository(private val dao: NotaDao, private val webClient: NotaWebClient) {

    fun buscaTodas(): Flow<List<Nota>> {
        return dao.buscaTodas()
    }
    suspend fun atualizaTodas(){
        webClient.buscaTodas()?.let{ notas ->
            dao.salvaLista(notas)

        }
    }
}