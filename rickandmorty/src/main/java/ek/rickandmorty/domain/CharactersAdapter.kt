package ek.rickandmorty.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ek.rickandmorty.presentation.viewholder.CharacterViewHolder
import ek.core.model.Character
import ek.rickandmorty.databinding.CharacterViewHolderBinding

class CharactersAdapter(
    private val onClick: (Character) -> Unit
) : RecyclerView.Adapter<CharacterViewHolder>() {

    private var characters: MutableList<Character> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(
            CharacterViewHolderBinding.inflate(inflater, parent, false)
        ) { onClick(it) }
    }

    override fun onBindViewHolder(
        holder: CharacterViewHolder,
        position: Int
    ) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size

    fun setItems(
        items: List<Character>
    ) {
        characters.addAll(items)
        notifyDataSetChanged()
    }

    fun setItem(
        item: Character
    ) {
        characters.add(item)
        notifyDataSetChanged()
    }


}