package ek.rickandmorty.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ek.network.model.Episode
import ek.rickandmorty.databinding.EpisodeViewHolderBinding
import ek.rickandmorty.presentation.viewholder.EpisodeViewHolder

class EpisodesAdapter(
    private val onClick: (Episode) -> Unit
) : RecyclerView.Adapter<EpisodeViewHolder>() {

    private var episodes: List<Episode> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return EpisodeViewHolder(
            EpisodeViewHolderBinding.inflate(inflater, parent, false)
        ) { onClick(it) }
    }

    override fun onBindViewHolder(
        holder: EpisodeViewHolder,
        position: Int
    ) {
        holder.bind(episodes[position])
    }

    override fun getItemCount(): Int = episodes.size

    fun setItems(
        items: List<Episode>
    ) {
        episodes = items
        notifyDataSetChanged()
    }
}