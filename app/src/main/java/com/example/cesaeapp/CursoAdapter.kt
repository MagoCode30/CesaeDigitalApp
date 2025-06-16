package com.example.cesaeapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cesaeapp.databinding.ItemCursoBinding
import com.example.cesaeapp.model.Curso

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
        holder.binding.txtNomeCurso.text = curso.nome
        holder.binding.txtLocal.text = curso.local
        holder.binding.txtDataInicio.text = "Início: ${curso.dataInicio}"
        holder.binding.txtPreco.text = "${curso.preco} €"
        val context = holder.itemView.context
        val resourceId = context.resources.getIdentifier(
            curso.imagem, "drawable", context.packageName
        )
        holder.binding.imgCurso.setImageResource(resourceId)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetalhesCurso::class.java)
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

    fun submitList(newCursos: List<Curso>) {
        cursos = newCursos
        notifyDataSetChanged()
    }
}
