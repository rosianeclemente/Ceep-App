package com.example.ceep.webclient.modelResposta

import com.example.ceep.model.Nota

class NotaResposta(
    val id: String?,
    val titulo: String?,
    val descricao: String?,
    val imagem: String?,
) {
    val nota: Nota
        get() = Nota(
            id = 0,
            titulo = titulo ?: "",
            descricao = descricao ?: "",
            imagem = imagem ?: ""
        )
}