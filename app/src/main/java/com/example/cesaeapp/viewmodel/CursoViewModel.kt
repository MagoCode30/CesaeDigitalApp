package com.example.cesaeapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.cesaeapp.repository.CursoRepository
import com.example.cesaeapp.model.Curso
import com.example.cesaeapp.database.AppDatabase
import kotlinx.coroutines.launch

/**
 * ViewModel para a entidade Curso.
 * Responsável por preparar e gerir os dados para a UI.
 */
class CursoViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * Enumeração para as opções de ordenação dos cursos.
     */
    enum class OrdemCurso {
        NOME_ASC, NOME_DESC, DATA_ASC, DATA_DESC
    }

    private val repository: CursoRepository
    val allCursos: LiveData<List<Curso>>

    // Ordem de apresentação dos cursos (por defeito: DATA_DESC)
    private val _ordem = MutableLiveData<OrdemCurso>(OrdemCurso.DATA_DESC)
    val ordem: LiveData<OrdemCurso> = _ordem

    init {
        // Inicializa o repository e obtém o DAO
        val cursoDao = AppDatabase.getDatabase(application).cursoDao()
        repository = CursoRepository(cursoDao)
        allCursos = repository.allCursos
    }

    // Exposição dos cursos de acordo com a ordem selecionada
    val cursosOrdenados: LiveData<List<Curso>> = _ordem.switchMap { ordem ->
        when (ordem) {
            OrdemCurso.NOME_ASC -> getCursosByNomeAsc()
            OrdemCurso.NOME_DESC -> getCursosByNomeDesc()
            OrdemCurso.DATA_ASC -> getCursosByDataAsc()
            OrdemCurso.DATA_DESC -> allCursos // Por defeito: por data desc
        }
    }

    /**
     * Altera a ordem de visualização dos cursos.
     */
    fun mudarOrdem(ordem: OrdemCurso) {
        _ordem.value = ordem
    }

    /**
     * Métodos para aceder às diferentes ordenações.
     */
    fun getCursosByNomeAsc(): LiveData<List<Curso>> = repository.getAllByNomeAsc()
    fun getCursosByNomeDesc(): LiveData<List<Curso>> = repository.getAllByNomeDesc()
    fun getCursosByDataAsc(): LiveData<List<Curso>> = repository.getAllByDataAsc()

    /**
     * Insere um novo curso.
     */
    fun insert(curso: Curso) = viewModelScope.launch {
        repository.insert(curso)
    }

    /**
     * Atualiza um curso existente.
     */
    fun update(curso: Curso) = viewModelScope.launch {
        repository.update(curso)
    }

    /**
     * Remove um curso.
     */
    fun delete(curso: Curso) = viewModelScope.launch {
        repository.delete(curso)
    }

    /**
     * Obtém um curso por ID (assincrono, com callback).
     */
    fun getById(id: Int, onResult: (Curso?) -> Unit) {
        viewModelScope.launch {
            val curso = repository.getById(id)
            onResult(curso)
        }
    }

    /**
     * Atualiza um curso (método suspenso alternativo).
     */
    suspend fun updateCurso(curso: Curso) {
        repository.update(curso)
    }
}

