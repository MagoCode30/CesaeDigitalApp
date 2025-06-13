package com.example.cesaeapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cesaeapp.model.Curso

@Dao
interface CursoDao {

    @Query("SELECT * FROM cursos ORDER BY nome ASC")
    fun getAllByNomeAsc(): LiveData<List<Curso>>

    @Query("SELECT * FROM cursos ORDER BY nome DESC")
    fun getAllByNomeDesc(): LiveData<List<Curso>>

    @Query("SELECT * FROM cursos ORDER BY dataInicio ASC")
    fun getAllByDataAsc(): LiveData<List<Curso>>

    @Query("SELECT * FROM cursos ORDER BY dataInicio DESC")
    fun getAllByDataDesc(): LiveData<List<Curso>>

    @Query("SELECT * FROM cursos")
    fun getAll(): LiveData<List<Curso>>

    @Insert
    suspend fun insert(curso: Curso)

    @Update
    suspend fun update(curso: Curso)

    @Delete
    suspend fun delete(curso: Curso)

    @Query("SELECT * FROM cursos WHERE id = :id")
    suspend fun getById(id: Int): Curso?
}