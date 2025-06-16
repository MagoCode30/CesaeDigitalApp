package com.example.cesaeapp.projetos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cesaeapp.R
import com.example.cesaeapp.databinding.ActivityProjetosBinding

/**
 * Activity "Projetos" - mostra projetos do CESAE em formato de cards estáticos.
 * Pronto para expansão dinâmica.
 */
class ProjetosActivity : AppCompatActivity() {
    // Binding para acesso seguro às views
    private lateinit var binding: ActivityProjetosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityProjetosBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Ajusta system bars (edge-to-edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configura toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Projetos"
    }

    /**
     * Handler do botão "voltar" da toolbar.
     */
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
