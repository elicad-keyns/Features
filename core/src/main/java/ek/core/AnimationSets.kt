package ek.core

import ek.base.R

class AnimationSets {
    companion object {
        val CROSS_FADE = intArrayOf(
            R.animator.enter_alpha,
            R.animator.exit_alpha,
            R.animator.enter_alpha,
            R.animator.exit_alpha
        )

        val SLIDER = intArrayOf(
            R.animator.enter_slide,
            R.animator.exit_slide,
            R.animator.pop_enter_slide,
            R.animator.pop_exit_slide
        )

        val SLIDER_VERTICAL = intArrayOf(
            R.animator.enter_slide_vertical,
            R.animator.exit_slide_vertical,
            R.animator.pop_enter_slide_vertical,
            R.animator.pop_exit_slide_vertical
        )

        val SLIDER_HORIZONTAL = intArrayOf(
            R.animator.enter_slide_horizontal,
            R.animator.exit_slide_horizontal,
            R.animator.pop_enter_slide_horizontal,
            R.animator.pop_exit_slide_horizontal
        )
    }
}