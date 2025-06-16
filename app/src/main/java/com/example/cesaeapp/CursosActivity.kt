package com.example.cesaeapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cesaeapp.databinding.ActivityCursosBinding
import com.example.cesaeapp.viewmodel.CursoViewModel

class CursosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCursosBinding
    private lateinit var adapter: CursoAdapter

    private val viewModel: CursoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCursosBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Cursos"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = CursoAdapter(listOf())
        binding.listaCursos.adapter = adapter
        binding.listaCursos.layoutManager = LinearLayoutManager(this)

        viewModel.cursosOrdenados.observe(this) { cursos ->
            adapter.submitList(cursos)
        }

        // Listener para os botões de ordenação (MaterialButtonToggleGroup)
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
        binding.fabAdicionar.setOnClickListener {
            val intent = Intent(this, AdicionarCursoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
