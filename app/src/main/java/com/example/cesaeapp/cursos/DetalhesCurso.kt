package com.example.cesaeapp.cursos

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cesaeapp.R
import com.example.cesaeapp.databinding.ActivityDetalhesCursoBinding
import com.example.cesaeapp.viewmodel.CursoViewModel

/**
 * Activity que mostra os detalhes de um curso, permite editar ou remover.
 */
class DetalhesCurso : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesCursoBinding
    private val viewModel: CursoViewModel by viewModels()

    // Launcher para editar e atualizar dados ao voltar
    private val editarLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val idCurso = result.data?.getIntExtra("id", -1) ?: -1
            if (idCurso != -1) {
                // Recarrega dados do curso atualizado
                viewModel.getById(idCurso) { curso ->
                    curso?.let {
                        runOnUiThread { atualizarUI(it) }
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesCursoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Ajusta para system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detalhes do Curso"

        carregarDadosDaIntent()

        // Remover curso com confirmação
        binding.btnRemover.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Remover Curso")
                .setMessage("Tem a certeza que deseja remover este curso?")
                .setPositiveButton("Sim") { _, _ ->
                    val idCurso = intent.getIntExtra("id", -1)
                    if (idCurso != -1) {
                        viewModel.getById(idCurso) { curso ->
                            curso?.let {
                                viewModel.delete(it)
                                finish()
                            }
                        }
                    }
                }
                .setNegativeButton("Não", null)
                .show()
        }

        // Editar curso (vai buscar pelo id)
        binding.btnEditar.setOnClickListener {
            val editIntent = Intent(this, EditarCursoActivity::class.java)
            editIntent.putExtra("id", intent.getIntExtra("id", -1))
            editarLauncher.launch(editIntent)
        }
    }

    /**
     * Carrega os dados passados pela Intent (primeiro carregamento).
     */
    private fun carregarDadosDaIntent() {
        binding.txtNomeCurso.text = intent.getStringExtra("nome")
        val nomeImagem = intent.getStringExtra("imagem")
        val resourceId = resources.getIdentifier(nomeImagem ?: "", "drawable", packageName)
        binding.imgCurso.setImageResource(resourceId)
        binding.txtLocal.text = "Local: " + intent.getStringExtra("local")
        binding.txtDataInicio.text = "Início: " + intent.getStringExtra("dataInicio")
        binding.txtDataFim.text = "Fim: " + intent.getStringExtra("dataFim")
        binding.txtDuracao.text = "Duração: " + intent.getStringExtra("duracao")
        binding.txtEdicao.text = "Edição: " + intent.getStringExtra("edicao")
        binding.txtPreco.text = "Preço: " + intent.getDoubleExtra("preco", 0.0) + " €"
    }

    /**
     * Atualiza os dados da UI após edição (fetch do Room).
     */
    private fun atualizarUI(curso: com.example.cesaeapp.model.Curso) {
        binding.txtNomeCurso.text = curso.nome
        val resourceId = resources.getIdentifier(curso.imagem, "drawable", packageName)
        binding.imgCurso.setImageResource(resourceId)
        binding.txtLocal.text = "Local: ${curso.local}"
        binding.txtDataInicio.text = "Início: ${curso.dataInicio}"
        binding.txtDataFim.text = "Fim: ${curso.dataFim}"
        binding.txtDuracao.text = "Duração: ${curso.duracao}"
        binding.txtEdicao.text = "Edição: ${curso.edicao}"
        binding.txtPreco.text = "Preço: ${curso.preco} €"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}

