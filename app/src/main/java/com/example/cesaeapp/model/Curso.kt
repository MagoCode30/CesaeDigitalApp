package com.example.cesaeapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidade Room que representa um curso.
 * Esta classe define a estrutura da tabela 'cursos'.
 */
@Entity(tableName = "cursos")
data class Curso(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // ID auto-gerado
    val nome: String,          // Nome do curso
    val local: String,         // Localização
    val dataInicio: String,    // Data de início (formato YYYY-MM-DD)
    val dataFim: String,       // Data de fim
    val preco: Double,         // Preço do curso
    val duracao: String,       // Duração (ex: "300 h")
    val edicao: String,        // Edição do curso
    val imagem: String         // Nome da imagem associada
)