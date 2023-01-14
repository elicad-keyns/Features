package ek.core

import androidx.fragment.app.Fragment

interface Navigation {
    fun navigate(tag: String, fragment: Fragment, animationSets: IntArray)

    fun replace(tag: String, fragment: Fragment, animationSets: IntArray)
}