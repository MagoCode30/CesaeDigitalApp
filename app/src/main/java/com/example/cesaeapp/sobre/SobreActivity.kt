package com.example.cesaeapp.sobre

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cesaeapp.R
import com.example.cesaeapp.databinding.ActivitySobreBinding

/**
 * Activity "Sobre" que apresenta informação sobre o CESAE e o programador.
 * Inclui link direto para o perfil do LinkedIn do autor.
 */
class SobreActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySobreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySobreBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Sobre"

        // Torna o texto do LinkedIn clicável e abre o perfil no browser
        val linkedinView = findViewById<TextView>(R.id.linkLinkedin)
        linkedinView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.linkedin.com/in/luismago-dev/")
            startActivity(intent)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}