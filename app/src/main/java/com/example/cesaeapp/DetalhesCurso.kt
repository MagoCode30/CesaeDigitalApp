package com.example.cesaeapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cesaeapp.databinding.ActivityDetalhesCursoBinding

class DetalhesCurso : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesCursoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesCursoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detalhes do Curso"

        binding.txtNomeCurso.text = intent.getStringExtra("nome")
        binding.imgCurso.setImageResource(intent.getIntExtra("imagemResId", R.drawable.cesaelogo))
        binding.txtLocal.text = "Local: " + intent.getStringExtra("local")
        binding.txtDataInicio.text = "Início: " + intent.getStringExtra("dataInicio")
        binding.txtDataFim.text = "Fim: " + intent.getStringExtra("dataFim")
        binding.txtDuracao.text = "Duração: " + intent.getStringExtra("duracao")
        binding.txtEdicao.text = "Edição: " + intent.getStringExtra("edicao")
        binding.txtPreco.text = "Preço: " + intent.getDoubleExtra("preco", 0.0) + " €"
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}