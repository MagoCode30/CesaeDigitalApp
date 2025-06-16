package com.example.cesaeapp

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

class AdicionarCursoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdicionarCursoBinding
    private val viewModel: CursoViewModel by viewModels()

    // Nomes das imagens disponíveis
    private val imagensDisponiveis = listOf(
        "bancoimagem1", "bancoimagem2", "bancoimagem3", "bancoimagem4", "bancoimagem5"
    )
    private var imagemSelecionada: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdicionarCursoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura a RecyclerView horizontal para imagens
        val adapter = ImagemEscolhaAdapter(imagensDisponiveis) { imagem ->
            imagemSelecionada = imagem
        }
        binding.rvImagens.adapter = adapter
        binding.rvImagens.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Por defeito, seleciona a primeira imagem
        imagemSelecionada = imagensDisponiveis.first()

        binding.editDataInicio.setOnClickListener {
            mostrarDatePicker(binding.editDataInicio)
        }
        binding.editDataFim.setOnClickListener {
            mostrarDatePicker(binding.editDataFim)
        }

        // Botão Guardar
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
            finish()
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
