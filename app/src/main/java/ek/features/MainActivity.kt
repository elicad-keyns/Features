package ek.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import ek.core.AnimationSets
import ek.core.Navigation
import ek.core.Toolbar
import ek.features.databinding.ActivityMainBinding
import ek.features.ui.main.MainFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigation, Toolbar {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater).apply {
            getString(R.string.app_name)
        }

        setContentView(binding.root)
        if (savedInstanceState == null) {
            replace(
                MainFragment.TAG,
                MainFragment.newInstance(),
                AnimationSets.CROSS_FADE
            )
        }
    }

    override fun navigate(
        tag: String,
        fragment: Fragment,
        animationSets: IntArray
    ) {
        supportFragmentManager.commit {
            setCustomAnimations(
                animationSets[0],
                animationSets[1],
                animationSets[2],
                animationSets[3],
            )
            replace(
                R.id.container,
                fragment,
                fragment.tag
            )
            addToBackStack(null)
        }
    }

    override fun replace(
        tag: String,
        fragment: Fragment,
        animationSets: IntArray
    ) {
        supportFragmentManager.commit {
            setCustomAnimations(
                animationSets[0],
                animationSets[1],
                animationSets[2],
                animationSets[3],
            )
            replace(
                R.id.container,
                fragment,
                fragment.tag
            )
        }
    }

    override fun getToolbar(): MaterialToolbar = binding.toolbar
}