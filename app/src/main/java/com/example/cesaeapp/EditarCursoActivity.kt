package com.example.cesaeapp

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cesaeapp.databinding.ActivityAdicionarCursoBinding
import com.example.cesaeapp.model.Curso
import com.example.cesaeapp.viewmodel.CursoViewModel
import kotlinx.coroutines.launch
import java.util.Calendar

class EditarCursoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdicionarCursoBinding
    private val viewModel: CursoViewModel by viewModels()
    private val imagensDisponiveis = listOf("bancoimagem1", "bancoimagem2", "bancoimagem3", "bancoimagem4", "bancoimagem5")
    private var imagemSelecionada: String? = null
    private var cursoId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdicionarCursoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Editar Curso"
        binding.toolbar.setNavigationOnClickListener { finish() }

        // ID do curso a editar
        cursoId = intent.getIntExtra("id", -1)
        if (cursoId == -1) {
            Toast.makeText(this, "Erro ao carregar curso!", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // RecyclerView horizontal para imagens
        val adapter = ImagemEscolhaAdapter(imagensDisponiveis) { imagem ->
            imagemSelecionada = imagem
        }
        binding.rvImagens.adapter = adapter
        binding.rvImagens.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Configurar DatePicker nos campos de data
        binding.editDataInicio.setOnClickListener {
            mostrarDatePicker(binding.editDataInicio)
        }
        binding.editDataFim.setOnClickListener {
            mostrarDatePicker(binding.editDataFim)
        }

        // Carregar dados do curso e preencher o formulário
        viewModel.getById(cursoId) { curso ->
            curso?.let {
                runOnUiThread {
                    binding.editNome.setText(it.nome)
                    binding.editLocal.setText(it.local)
                    binding.editDataInicio.setText(it.dataInicio)
                    binding.editDataFim.setText(it.dataFim)
                    binding.editPreco.setText(it.preco.toString())
                    binding.editDuracao.setText(it.duracao)
                    binding.editEdicao.setText(it.edicao)
                    imagemSelecionada = it.imagem
                }
            }
        }

        // Botão Guardar faz update!
        binding.btnGuardar.text = "Guardar alterações"
        binding.btnGuardar.setOnClickListener {
            binding.btnGuardar.isEnabled = false

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)

            lifecycleScope.launch {
                val cursoAtualizado = Curso(
                    id = cursoId,
                    nome = binding.editNome.text.toString(),
                    local = binding.editLocal.text.toString(),
                    dataInicio = binding.editDataInicio.text.toString(),
                    dataFim = binding.editDataFim.text.toString(),
                    preco = binding.editPreco.text.toString().toDoubleOrNull() ?: 0.0,
                    duracao = binding.editDuracao.text.toString(),
                    edicao = binding.editEdicao.text.toString(),
                    imagem = imagemSelecionada ?: imagensDisponiveis[0]
                )
                viewModel.updateCurso(cursoAtualizado)

                Toast.makeText(this@EditarCursoActivity, "Curso atualizado!", Toast.LENGTH_SHORT).show()

                val resultIntent = Intent().apply {
                    putExtra("id", cursoId)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    private fun mostrarDatePicker(editText: EditText) {
        val cal = Calendar.getInstance()
        val dpd = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val data = "%04d-%02d-%02d".format(year, month + 1, dayOfMonth)
                editText.setText(data)
            },
            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
        )
        dpd.show()
    }
}
