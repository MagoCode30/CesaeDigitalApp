package com.example.cesaeapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cursos")
data class Curso(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val local: String,
    val dataInicio: String,
    val dataFim: String,
    val preco: Double,
    val duracao: String,
    val edicao: String,
    val imagem: String
)
