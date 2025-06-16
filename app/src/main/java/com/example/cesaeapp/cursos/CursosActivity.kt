package com.example.cesaeapp.cursos

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cesaeapp.R
import com.example.cesaeapp.databinding.ActivityCursosBinding
import com.example.cesaeapp.viewmodel.CursoViewModel

/**
 * Activity que mostra a lista de cursos com ordenação dinâmica.
 * Permite adicionar novos cursos e consultar detalhes.
 */
class CursosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCursosBinding
    private lateinit var adapter: CursoAdapter

    // ViewModel para gestão de dados do Room
    private val viewModel: CursoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCursosBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Configuração da Toolbar com botão de navegação
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Cursos"

        // Ajuste de layout para system bars (edge-to-edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuração do RecyclerView
        adapter = CursoAdapter(listOf())
        binding.listaCursos.adapter = adapter
        binding.listaCursos.layoutManager = LinearLayoutManager(this)

        // Observa alterações na lista (ordenada) de cursos
        viewModel.cursosOrdenados.observe(this) { cursos ->
            adapter.submitList(cursos)
        }

        // Grupo de botões para ordenação dinâmica da lista
        binding.toggleGroupOrder.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btnNomeAsc -> viewModel.mudarOrdem(CursoViewModel.OrdemCurso.NOME_ASC)
                    R.id.btnNomeDesc -> viewModel.mudarOrdem(CursoViewModel.OrdemCurso.NOME_DESC)
                    R.id.btnDataAsc -> viewModel.mudarOrdem(CursoViewModel.OrdemCurso.DATA_ASC)
                    R.id.btnDataDesc -> viewModel.mudarOrdem(CursoViewModel.OrdemCurso.DATA_DESC)
                }
            }
        }

        // FloatingActionButton para adicionar novo curso
        binding.fabAdicionar.setOnClickListener {
            val intent = Intent(this, AdicionarCursoActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Handler para o botão de voltar da toolbar.
     */
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}

