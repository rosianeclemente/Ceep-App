package com.example.ceep.webclient.modelResposta

import com.example.ceep.model.Nota
import java.util.*

class NotaResposta(
    val id: String?,
    val titulo: String?,
    val descricao: String?,
    val imagem: String?,
) {
    val nota: Nota
        get() = Nota(
            id = id ?: UUID.randomUUID().toString(),
            titulo = titulo ?: "",
            descricao = descricao ?: "",
            imagem = imagem ?: ""
        )
}