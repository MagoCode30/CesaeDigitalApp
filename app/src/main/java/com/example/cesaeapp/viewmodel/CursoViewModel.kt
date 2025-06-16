package com.example.cesaeapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.cesaeapp.repository.CursoRepository
import com.example.cesaeapp.model.Curso
import com.example.cesaeapp.database.AppDatabase
import kotlinx.coroutines.launch


class CursoViewModel(application: Application) : AndroidViewModel(application) {

    enum class OrdemCurso {
        NOME_ASC, NOME_DESC, DATA_ASC, DATA_DESC
    }


    private val repository: CursoRepository
    val allCursos: LiveData<List<Curso>>

    private val _ordem = MutableLiveData<OrdemCurso>(OrdemCurso.DATA_DESC)
    val ordem: LiveData<OrdemCurso> = _ordem

    init {
        val cursoDao = AppDatabase.getDatabase(application).cursoDao()
        repository = CursoRepository(cursoDao)
        allCursos = repository.allCursos
    }

    val cursosOrdenados: LiveData<List<Curso>> = _ordem.switchMap { ordem ->
        when (ordem) {
            OrdemCurso.NOME_ASC -> getCursosByNomeAsc()
            OrdemCurso.NOME_DESC -> getCursosByNomeDesc()
            OrdemCurso.DATA_ASC -> getCursosByDataAsc()
            OrdemCurso.DATA_DESC -> allCursos // Padrão: por data desc
        }
    }

    fun mudarOrdem(ordem: OrdemCurso) {
        _ordem.value = ordem
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

    suspend fun updateCurso(curso: Curso) {
        repository.update(curso)
    }
}


