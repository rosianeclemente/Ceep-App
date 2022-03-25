package com.example.ceep.ui.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ceep.databinding.NotaItemBinding
import com.example.ceep.extensions.tentaCarregarImagem
import com.example.ceep.model.Nota

class ListaNotasAdapter(
    private val context: Context,
    var quandoClicaNoItem: (nota: Nota) -> Unit = {},
    notas: List<Nota> = emptyList()
) : RecyclerView.Adapter<ListaNotasAdapter.ViewHolder>() {

    private val notas = notas.toMutableList()

    class ViewHolder(
        private val binding: NotaItemBinding,
        private val quandoClicaNoItem: (nota: Nota) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var nota: Nota

        init {
            itemView.setOnClickListener {
                if (::nota.isInitialized) {
                    quandoClicaNoItem(nota)
                }
            }
        }

        fun vincula(nota: Nota) {
            this.nota = nota
            val imagemNota = binding.notaItemImagem
            imagemNota.visibility =
                if (nota.imagem.isNullOrBlank()) {
                    View.GONE
                } else {
                    imagemNota.tentaCarregarImagem(nota.imagem)
                    View.VISIBLE
                }
            binding.notaItemTitulo.text = nota.titulo
            binding.notaItemDescricao.text = nota.descricao
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder =
        ViewHolder(
            NotaItemBinding
                .inflate(
                    LayoutInflater.from(context)
                ),
            quandoClicaNoItem
        )

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.vincula(notas[position])
    }

    override fun getItemCount(): Int = notas.size

    fun atualiza(notas: List<Nota>) {
        notifyItemRangeRemoved(0, this.notas.size)
        this.notas.clear()
        this.notas.addAll(notas)
        notifyItemInserted(this.notas.size)
    }

}