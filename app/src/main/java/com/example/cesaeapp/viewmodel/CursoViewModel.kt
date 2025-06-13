package com.example.cesaeapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.cesaeapp.repository.CursoRepository
import com.example.cesaeapp.model.Curso
import com.example.cesaeapp.database.AppDatabase
import kotlinx.coroutines.launch

class CursoViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CursoRepository
    val allCursos: LiveData<List<Curso>>

    init {
        val cursoDao = AppDatabase.getDatabase(application).cursoDao()
        repository = CursoRepository(cursoDao)
        allCursos = repository.allCursos
    }

    fun getCursosByNomeAsc(): LiveData<List<Curso>> = repository.getAllByNomeAsc()
    fun getCursosByNomeDesc(): LiveData<List<Curso>> = repository.getAllByNomeDesc()
    fun getCursosByDataAsc(): LiveData<List<Curso>> = repository.getAllByDataAsc()

    fun insert(curso: Curso) = viewModelScope.launch {
        repository.insert(curso)
    }

    fun update(curso: Curso) = viewModelScope.launch {
        repository.update(curso)
    }

    fun delete(curso: Curso) = viewModelScope.launch {
        repository.delete(curso)
    }

    fun getById(id: Int, onResult: (Curso?) -> Unit) {
        viewModelScope.launch {
            val curso = repository.getById(id)
            onResult(curso)
        }
    }
}