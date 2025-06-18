package com.example.cesaeapp.cursos

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cesaeapp.databinding.ItemCursoBinding
import com.example.cesaeapp.model.Curso

/**
 * Adapter do RecyclerView para listar cursos.
 * Liga a lista de objetos Curso ao layout de cada item.
 */
class CursoAdapter(
    private var cursos: List<Curso>
) : RecyclerView.Adapter<CursoAdapter.CursoViewHolder>() {

    inner class CursoViewHolder(val binding: ItemCursoBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
        val binding = ItemCursoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return CursoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
        val curso = cursos[position]
        // Preenche os dados do curso no layout do item
        holder.binding.txtNomeCurso.text = curso.nome
        holder.binding.txtLocal.text = curso.local
        holder.binding.txtDataInicio.text = "Início: ${curso.dataInicio}"
        holder.binding.txtPreco.text = "${curso.preco} €"
        // Obtém a imagem associada ao curso dinamicamente
        val context = holder.itemView.context
        val resourceId = context.resources.getIdentifier(
            curso.imagem, "drawable", context.packageName
        )
        holder.binding.imgCurso.setImageResource(resourceId)

        // Ao clicar no item, abre a activity de detalhes do curso
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetalhesCurso::class.java)
            // Passa todos os campos do curso via intent
            intent.putExtra("id", curso.id)
            intent.putExtra("nome", curso.nome)
            intent.putExtra("imagem", curso.imagem)
            intent.putExtra("local", curso.local)
            intent.putExtra("dataInicio", curso.dataInicio)
            intent.putExtra("dataFim", curso.dataFim)
            intent.putExtra("duracao", curso.duracao)
            intent.putExtra("edicao", curso.edicao)
            intent.putExtra("preco", curso.preco)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = cursos.size

    /**
     * Atualiza a lista de cursos e notifica o RecyclerView.
     */
    fun submitList(newCursos: List<Curso>) {
        cursos = newCursos
        notifyDataSetChanged()
    }
}
