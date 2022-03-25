package com.example.ceep.ui.dialog

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.ceep.R
import com.example.ceep.databinding.FormImagemBinding
import com.example.ceep.extensions.tentaCarregarImagem

class FormImagemDialog(private val context: Context) {
    fun mostra(
        urlPadrao: String? = null,
        quandoImagemCarragada: (imagem: String) -> Unit
    ) {
        FormImagemBinding.inflate(LayoutInflater.from(context)).apply {

            urlPadrao?.let {
                formImagemImageview.tentaCarregarImagem(it)
                formImagemUrl.setText(it)
            }

            formImagemBotaoCarregar.setOnClickListener {
                val url = formImagemUrl.text.toString()
                formImagemImageview.tentaCarregarImagem(url)
            }

            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = formImagemUrl.text.toString()
                    quandoImagemCarragada(url)
                }
                .setNegativeButton("Cancelar") { _, _ ->

                }
                .show()
        }


    }
}