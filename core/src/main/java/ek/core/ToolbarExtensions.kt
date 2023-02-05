package ek.core

import androidx.annotation.DrawableRes
import com.google.android.material.appbar.MaterialToolbar
import ek.base.R

inline fun MaterialToolbar.setNavigationIcon(
    @DrawableRes icon: Int = R.drawable.back_arrow,
    crossinline onClick: () -> Unit
) {
    setNavigationIcon(icon)
    setNavigationOnClickListener { onClick() }
}