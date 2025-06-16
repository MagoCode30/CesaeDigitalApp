package com.example.cesaeapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cesaeapp.model.Curso

/**
 * DAO (Data Access Object) para a entidade Curso.
 * Define métodos de acesso e manipulação da tabela 'cursos'.
 */
@Dao
interface CursoDao {

    // Retorna todos os cursos ordenados por nome (ascendente)
    @Query("SELECT * FROM cursos ORDER BY nome ASC")
    fun getAllByNomeAsc(): LiveData<List<Curso>>

    // Retorna todos os cursos ordenados por nome (descendente)
    @Query("SELECT * FROM cursos ORDER BY nome DESC")
    fun getAllByNomeDesc(): LiveData<List<Curso>>

    // Retorna todos os cursos ordenados pela data de início (ascendente)
    @Query("SELECT * FROM cursos ORDER BY dataInicio ASC")
    fun getAllByDataAsc(): LiveData<List<Curso>>

    // Retorna todos os cursos ordenados pela data de início (descendente)
    @Query("SELECT * FROM cursos ORDER BY dataInicio DESC")
    fun getAllByDataDesc(): LiveData<List<Curso>>

    // Retorna todos os cursos sem ordem específica
    @Query("SELECT * FROM cursos")
    fun getAll(): LiveData<List<Curso>>

    // Insere um novo curso na base de dados
    @Insert
    suspend fun insert(curso: Curso)

    // Atualiza um curso existente
    @Update
    suspend fun update(curso: Curso)

    // Remove um curso da base de dados
    @Delete
    suspend fun delete(curso: Curso)

    // Retorna um curso pelo ID
    @Query("SELECT * FROM cursos WHERE id = :id")
    suspend fun getById(id: Int): Curso?
}
