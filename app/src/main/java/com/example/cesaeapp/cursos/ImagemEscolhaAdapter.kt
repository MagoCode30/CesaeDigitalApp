package com.example.cesaeapp.cursos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cesaeapp.databinding.ItemImagemEscolhaBinding

/**
 * Adapter horizontal para seleção visual de imagens.
 * Realça visualmente a imagem selecionada.
 */
class ImagemEscolhaAdapter(
    private val imagens: List<String>,
    private val onImagemSelecionada: (String) -> Unit
) : RecyclerView.Adapter<ImagemEscolhaAdapter.ImagemViewHolder>() {

    // Guarda qual a imagem selecionada para feedback visual
    private var imagemSelecionada: String? = null

    inner class ImagemViewHolder(val binding: ItemImagemEscolhaBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagemViewHolder {
        val binding = ItemImagemEscolhaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ImagemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagemViewHolder, position: Int) {
        val nomeImagem = imagens[position]
        val context = holder.itemView.context
        val resourceId = context.resources.getIdentifier(
            nomeImagem, "drawable", context.packageName
        )
        holder.binding.imgEscolha.setImageResource(resourceId)

        // Destaque visual para a imagem selecionada
        holder.binding.imgEscolha.alpha = if (nomeImagem == imagemSelecionada) 1.0f else 0.6f

        holder.itemView.setOnClickListener {
            val prevSelecionada = imagemSelecionada
            imagemSelecionada = nomeImagem
            notifyItemChanged(position)
            prevSelecionada?.let { prev ->
                val prevIndex = imagens.indexOf(prev)
                if (prevIndex != -1) notifyItemChanged(prevIndex)
            }
            onImagemSelecionada(nomeImagem)
        }
    }

    override fun getItemCount(): Int = imagens.size
}
