package ek.rickandmorty.presentation.viewholder

import com.bumptech.glide.Glide
import ek.core.BaseViewHolder
import ek.core.model.Character
import ek.rickandmorty.databinding.CharacterViewHolderBinding

class CharacterViewHolder(
    private val binding: CharacterViewHolderBinding,
    private val onClick: (Character) -> Unit
) : BaseViewHolder<Character>(binding.root) {

    override fun bind(item: Character) = with(binding) {
        root.setOnClickListener { onClick(item) }

        Glide
            .with(root)
            .load(item.image)
            .centerCrop()
            .into(characterImage)

        characterName.text = item.name
        characterSpecies.text = item.species
        characterStatus.text = item.status
        characterLastLocation.text = item.location.name
        characterFirstSeen.text = item.origin.name
    }

}