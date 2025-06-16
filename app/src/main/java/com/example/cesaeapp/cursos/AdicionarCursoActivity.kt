package com.example.cesaeapp.cursos

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cesaeapp.databinding.ActivityAdicionarCursoBinding
import com.example.cesaeapp.model.Curso
import com.example.cesaeapp.viewmodel.CursoViewModel
import java.util.Calendar

/**
 * Activity para adicionar um novo curso.
 * Inclui seleção de imagem, datas com DatePicker e validação simples.
 */
class AdicionarCursoActivity : AppCompatActivity() {

    // ViewBinding para aceder facilmente às views
    private lateinit var binding: ActivityAdicionarCursoBinding

    // ViewModel para interagir com a camada de dados
    private val viewModel: CursoViewModel by viewModels()

    // Lista de nomes de imagens disponíveis para seleção
    private val imagensDisponiveis = listOf(
        "bancoimagem1", "bancoimagem2", "bancoimagem3", "bancoimagem4", "bancoimagem5"
    )
    private var imagemSelecionada: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdicionarCursoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuração da toolbar com botão de back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Adicionar Curso"

        // Configura a RecyclerView horizontal para seleção de imagens
        val adapter = ImagemEscolhaAdapter(imagensDisponiveis) { imagem ->
            imagemSelecionada = imagem // Guarda imagem selecionada pelo utilizador
        }
        binding.rvImagens.adapter = adapter
        binding.rvImagens.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Por defeito, seleciona a primeira imagem
        imagemSelecionada = imagensDisponiveis.first()

        // Mostra DatePicker ao clicar nos campos de data
        binding.editDataInicio.setOnClickListener {
            mostrarDatePicker(binding.editDataInicio)
        }
        binding.editDataFim.setOnClickListener {
            mostrarDatePicker(binding.editDataFim)
        }

        // Botão Guardar: lê dados do formulário, cria objeto Curso, e envia para o ViewModel
        binding.btnGuardar.setOnClickListener {
            val curso = Curso(
                nome = binding.editNome.text.toString(),
                local = binding.editLocal.text.toString(),
                dataInicio = binding.editDataInicio.text.toString(),
                dataFim = binding.editDataFim.text.toString(),
                preco = binding.editPreco.text.toString().toDoubleOrNull() ?: 0.0,
                duracao = binding.editDuracao.text.toString(),
                edicao = binding.editEdicao.text.toString(),
                imagem = imagemSelecionada ?: imagensDisponiveis[0]
            )
            viewModel.insert(curso)
            finish() // Fecha a activity após guardar
        }
    }

    /**
     * Mostra um DatePickerDialog e coloca a data escolhida no EditText.
     */
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

    /**
     * Handler para o botão de voltar da toolbar.
     */
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
