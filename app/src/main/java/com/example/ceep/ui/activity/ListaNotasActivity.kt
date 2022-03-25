package com.example.ceep.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.ceep.database.AppDatabase
import com.example.ceep.databinding.ActivityListaNotasBinding
import com.example.ceep.extensions.vaiPara
import com.example.ceep.model.Nota
import com.example.ceep.ui.recyclerview.ListaNotasAdapter
import com.example.ceep.webclient.RetrofitInicializador
import com.example.ceep.webclient.modelResposta.NotaResposta
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class ListaNotasActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityListaNotasBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        ListaNotasAdapter(this)
    }
    private val dao by lazy {
        AppDatabase.instancia(this).notaDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraFab()
        configuraRecyclerView()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                buscaNotas()
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            val call: Call<List<NotaResposta>> = RetrofitInicializador().notasServices.buscarTodos()
            val resposta: Response<List<NotaResposta>> = call.execute()
            resposta.body()?.let { notasResposta ->
                val notas: List<Nota> = notasResposta.map {
                    it.nota
                }
                Log.i("Lista notas", "OnCreate: $notas")

            }
        }
    }





    private fun configuraFab() {
        binding.activityListaNotasFab.setOnClickListener {
            Intent(this, FormNotaActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    private fun configuraRecyclerView() {
        binding.activityListaNotasRecyclerview.adapter = adapter
        adapter.quandoClicaNoItem = { nota ->
            vaiPara(FormNotaActivity::class.java) {
                putExtra(NOTA_ID, nota.id)
            }
        }
    }

    private suspend fun buscaNotas() {
        dao.buscaTodas()
            .collect { notasEncontradas ->
                binding.activityListaNotasMensagemSemNotas.visibility =
                    if (notasEncontradas.isEmpty()) {
                        binding.activityListaNotasRecyclerview.visibility = View.GONE
                        View.VISIBLE
                    } else {
                        binding.activityListaNotasRecyclerview.visibility = View.VISIBLE
                        adapter.atualiza(notasEncontradas)
                        View.GONE
                    }
            }
    }
}