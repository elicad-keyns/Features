package ek.rickandmorty.presentation.viewholder

import ek.core.base.BaseViewHolder
import ek.network.model.Episode
import ek.rickandmorty.databinding.EpisodeViewHolderBinding

class EpisodeViewHolder(
    private val binding: EpisodeViewHolderBinding,
    private val onClick: (Episode) -> Unit
) : BaseViewHolder<Episode>(binding.root) {

    override fun bind(item: Episode) = with(binding) {
        root.setOnClickListener { onClick(item) }
        episodeDate.text = item.airDate
        episodeName.text = item.name
        episodeIndex.text = item.episode
    }

}