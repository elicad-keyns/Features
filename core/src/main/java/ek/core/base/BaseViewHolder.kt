package ek.core.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in Item: Any>(
    view: View
) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: Item)
}