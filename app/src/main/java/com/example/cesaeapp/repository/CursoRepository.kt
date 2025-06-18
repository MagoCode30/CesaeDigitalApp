package com.example.cesaeapp.repository

import androidx.lifecycle.LiveData
import com.example.cesaeapp.dao.CursoDao
import com.example.cesaeapp.model.Curso

/**
 * Repository para a entidade Curso.
 * Responsável pela abstração de acesso aos dados e regras de negócio.
 */
class CursoRepository(private val cursoDao: CursoDao) {

    // Lista principal (ordenada por data desc)
    val allCursos: LiveData<List<Curso>> = cursoDao.getAllByDataDesc()

    // Alternativas de ordenação
    fun getAllByNomeAsc(): LiveData<List<Curso>> = cursoDao.getAllByNomeAsc()
    fun getAllByNomeDesc(): LiveData<List<Curso>> = cursoDao.getAllByNomeDesc()
    fun getAllByDataAsc(): LiveData<List<Curso>> = cursoDao.getAllByDataAsc()

    // Insere um curso
    suspend fun insert(curso: Curso) {
        cursoDao.insert(curso)
    }

    // Atualiza um curso
    suspend fun update(curso: Curso) {
        cursoDao.update(curso)
    }

    // Remove um curso
    suspend fun delete(curso: Curso) {
        cursoDao.delete(curso)
    }

    // Retorna um curso por ID
    suspend fun getById(id: Int): Curso? {
        return cursoDao.getById(id)
    }
}
