package ek.features.ui.viewholder

import android.content.res.ColorStateList
import ek.core.base.BaseViewHolder
import ek.core.model.Feature
import ek.features.databinding.FeatureViewHolderBinding

class FeatureViewHolder(
    private val binding: FeatureViewHolderBinding,
    private val onClick: (Feature) -> Unit
) : BaseViewHolder<Feature>(binding.root) {

    override fun bind(item: Feature) = with(binding) {
        root.setCardBackgroundColor(ColorStateList.valueOf(item.bgColor))

        root.setOnClickListener { onClick(item) }

        featureName.text = item.name
        featureDescription.text = item.description
        featureCategory.text = item.category
    }

}