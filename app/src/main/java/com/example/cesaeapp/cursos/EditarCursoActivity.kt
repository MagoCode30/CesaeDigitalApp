package com.example.cesaeapp.cursos

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

/**
 * Activity para editar um curso já existente.
 * Reutiliza o layout e lógica da AdicionarCursoActivity.
 */
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

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Editar Curso"
        binding.toolbar.setNavigationOnClickListener { finish() }

        // Verifica o id do curso a editar
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

        // Configura o DatePicker nos campos de data
        binding.editDataInicio.setOnClickListener {
            mostrarDatePicker(binding.editDataInicio)
        }
        binding.editDataFim.setOnClickListener {
            mostrarDatePicker(binding.editDataFim)
        }

        // Carrega os dados do curso e preenche o formulário
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

        // Botão guardar faz update em vez de insert!
        binding.btnGuardar.text = "Guardar alterações"
        binding.btnGuardar.setOnClickListener {
            // Esconde o teclado após guardar
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)

            // Leitura dos campos com trim()
            val nome = binding.editNome.text.toString().trim()
            val local = binding.editLocal.text.toString().trim()
            val dataInicio = binding.editDataInicio.text.toString().trim()
            val dataFim = binding.editDataFim.text.toString().trim()
            val precoText = binding.editPreco.text.toString().trim()
            val duracao = binding.editDuracao.text.toString().trim()
            val edicao = binding.editEdicao.text.toString().trim()
            val imagem = imagemSelecionada ?: imagensDisponiveis[0]

            // Validação obrigatória
            when {
                nome.isEmpty() -> {
                    Toast.makeText(this, "O nome é obrigatório.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                local.isEmpty() -> {
                    Toast.makeText(this, "O local é obrigatório.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                dataInicio.isEmpty() -> {
                    Toast.makeText(this, "A data de início é obrigatória.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                dataFim.isEmpty() -> {
                    Toast.makeText(this, "A data de fim é obrigatória.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                precoText.isEmpty() -> {
                    Toast.makeText(this, "O preço é obrigatório.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                precoText.toDoubleOrNull() == null || precoText.toDouble() <= 0 -> {
                    Toast.makeText(this, "O preço deve ser um número maior que zero.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                duracao.isEmpty() -> {
                    Toast.makeText(this, "A duração é obrigatória.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                edicao.isEmpty() -> {
                    Toast.makeText(this, "A edição é obrigatória.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            // Tudo OK, faz update!
            binding.btnGuardar.isEnabled = false

            lifecycleScope.launch {
                val cursoAtualizado = Curso(
                    id = cursoId,
                    nome = nome,
                    local = local,
                    dataInicio = dataInicio,
                    dataFim = dataFim,
                    preco = precoText.toDouble(),
                    duracao = duracao,
                    edicao = edicao,
                    imagem = imagem
                )
                viewModel.updateCurso(cursoAtualizado)

                Toast.makeText(this@EditarCursoActivity, "Curso atualizado com sucesso!", Toast.LENGTH_SHORT).show()

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
