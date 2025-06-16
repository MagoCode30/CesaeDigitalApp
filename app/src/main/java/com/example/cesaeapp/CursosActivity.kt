package com.example.cesaeapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cesaeapp.databinding.ActivityCursosBinding
import com.example.cesaeapp.viewmodel.CursoViewModel

class CursosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCursosBinding
    private lateinit var adapter: CursoAdapter


    private val viewModel: CursoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCursosBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Cursos"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        adapter = CursoAdapter(listOf())
        binding.listaCursos.adapter = adapter
        binding.listaCursos.layoutManager = LinearLayoutManager(this)


        viewModel.allCursos.observe(this) { cursos ->
            adapter.submitList(cursos)
        }

        }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
