package ek.features.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ek.core.model.Feature
import ek.features.databinding.FeatureViewHolderBinding
import ek.features.ui.viewholder.FeatureViewHolder


class FeaturesAdapter(
    private val onClick: (Feature) -> Unit
) : RecyclerView.Adapter<FeatureViewHolder>() {

    private var features: List<Feature> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return FeatureViewHolder(
            FeatureViewHolderBinding.inflate(inflater, parent, false)
        ) { onClick(it) }
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        holder.bind(features[position])
    }

    override fun getItemCount(): Int = features.size

    fun setItems(items: List<Feature>) {
        features = items
        notifyDataSetChanged()
    }
}