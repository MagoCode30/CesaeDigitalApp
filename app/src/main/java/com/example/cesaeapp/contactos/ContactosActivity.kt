package com.example.cesaeapp.contactos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cesaeapp.R
import com.example.cesaeapp.databinding.ActivityContactosBinding

/**
 * Activity "Contactos" - apresenta os contactos institucionais do CESAE.
 * Estrutura simples, apenas leitura.
 */
class ContactosActivity : AppCompatActivity() {
    // Binding para acesso seguro às views
    private lateinit var binding: ActivityContactosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactosBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Ajusta as system bars para um layout moderno (edge-to-edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configura toolbar Material
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Contactos"
    }

    /**
     * Handler do botão "voltar" da toolbar.
     */
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
