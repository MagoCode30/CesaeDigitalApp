package com.example.cesaeapp.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cesaeapp.R
import com.example.cesaeapp.contactos.ContactosActivity
import com.example.cesaeapp.cursos.CursosActivity
import com.example.cesaeapp.databinding.ActivityMainBinding
import com.example.cesaeapp.projetos.ProjetosActivity
import com.example.cesaeapp.servicos.ServicosActivity
import com.example.cesaeapp.sobre.SobreActivity

/**
 * Activity principal do CesaeApp.
 * Exibe o menu principal com navegação para as diferentes áreas do app.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Navegação para as diferentes Activities do menu principal
        binding.buttonCursos.setOnClickListener {
            startActivity(Intent(this, CursosActivity::class.java))
        }
        binding.buttonProjetos.setOnClickListener {
            startActivity(Intent(this, ProjetosActivity::class.java))
        }
        binding.buttonServicos.setOnClickListener {
            startActivity(Intent(this, ServicosActivity::class.java))
        }
        binding.buttonContactos.setOnClickListener {
            startActivity(Intent(this, ContactosActivity::class.java))
        }
        binding.buttonSobre.setOnClickListener {
            startActivity(Intent(this, SobreActivity::class.java))
        }
    }
}
