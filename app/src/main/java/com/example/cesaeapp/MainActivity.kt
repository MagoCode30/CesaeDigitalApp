package com.example.cesaeapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cesaeapp.databinding.ActivityMainBinding

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

        binding.buttonCursos.setOnClickListener() {
            startActivity(Intent(this,CursosActivity::class.java))
        }
        binding.buttonProjetos.setOnClickListener() {
            startActivity(Intent(this,ProjetosActivity::class.java))
        }
        binding.buttonServicos.setOnClickListener() {
            startActivity(Intent(this,ServicosActivity::class.java))
        }
        binding.buttonContactos.setOnClickListener() {
            startActivity(Intent(this,ContactosActivity::class.java))
        }
        binding.buttonSobre.setOnClickListener() {
            startActivity(Intent(this,SobreActivity::class.java))
        }
    }
}