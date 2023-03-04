package ek.rickandmorty.presentation.viewholder

import android.graphics.Color
import com.bumptech.glide.Glide
import ek.core.base.BaseViewHolder
import ek.network.model.Character
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
        with (characterStatus) {
            text = item.status
            if (item.status == "Alive")
                setTextColor(Color.GREEN)
            else
                setTextColor(Color.RED)
        }
        characterLastLocation.text = item.location.name
        characterFirstSeen.text = item.origin.name
    }

}