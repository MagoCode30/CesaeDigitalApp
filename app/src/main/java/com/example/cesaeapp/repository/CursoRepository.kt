package com.example.cesaeapp.repository

import androidx.lifecycle.LiveData
import com.example.cesaeapp.dao.CursoDao
import com.example.cesaeapp.model.Curso

class CursoRepository(private val cursoDao: CursoDao) {

    // Principal
    val allCursos: LiveData<List<Curso>> = cursoDao.getAllByDataDesc()

    // Alternativas
    fun getAllByNomeAsc(): LiveData<List<Curso>> = cursoDao.getAllByNomeAsc()
    fun getAllByNomeDesc(): LiveData<List<Curso>> = cursoDao.getAllByNomeDesc()
    fun getAllByDataAsc(): LiveData<List<Curso>> = cursoDao.getAllByDataAsc()

    suspend fun insert(curso: Curso) {
        cursoDao.insert(curso)
    }

    suspend fun update(curso: Curso) {
        cursoDao.update(curso)
    }

    suspend fun delete(curso: Curso) {
        cursoDao.delete(curso)
    }

    suspend fun getById(id: Int): Curso? {
        return cursoDao.getById(id)
    }
}