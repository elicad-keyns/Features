package ek.features.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ek.core.AnimationSets
import ek.core.Navigation
import ek.core.infrastructure.MviView
import ek.core.model.Feature
import ek.features.databinding.FragmentMainBinding
import ek.features.domain.FeaturesAdapter
import ek.rickandmorty.presentation.episodes.EpisodesFragment

@AndroidEntryPoint
class MainFragment : Fragment(), MviView<MainState, MainEvent> {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel>()


    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false).apply {
//            questions.setOnClickListener { startEpisodeScreen() }
            toolbar.title = getString(ek.base.R.string.app_name).uppercase()

            features.adapter = FeaturesAdapter { startFeatureScreen(it) }
            (features.adapter as FeaturesAdapter).setItems(generateFeatures())
        }

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.stateListener.observe(viewLifecycleOwner, ::renderViewState)
        viewModel.eventListener.observe(viewLifecycleOwner, ::handleViewEvent)
        viewModel.pushIntent(MainIntent.OnViewCreated)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun renderViewState(
        state: MainState
    ) = with(binding) {

    }

    override fun handleViewEvent(
        event: MainEvent
    ) {

    }

    private fun startFeatureScreen(feature: Feature) = with(activity as Navigation) {
        when (feature.screenTag) {
            EpisodesFragment.TAG -> startEpisodeScreen()
        }
    }

    private fun startEpisodeScreen() = with(activity as Navigation) {
        navigate(
            EpisodesFragment.TAG,
            EpisodesFragment.newInstance(),
            AnimationSets.CROSS_FADE
        )
    }

    private fun generateFeatures(): List<Feature> {
        val colors = listOf(
            ContextCompat.getColor(requireContext(), ek.base.R.color.light_blue),
            ContextCompat.getColor(requireContext(), ek.base.R.color.orange),
            ContextCompat.getColor(requireContext(), ek.base.R.color.light_green),
            ContextCompat.getColor(requireContext(), ek.base.R.color.yellow)
        )

        return listOf(
            Feature(
                "Rick And Morty",
                "Info about the Rick and Morty universe",
                "API",
                EpisodesFragment.TAG,
                colors[0]
            ),
            Feature(
                "Open AI (ChatGPT)",
                "Try artificial intelligence from OpenAI",
                "AI",
                EpisodesFragment.TAG,
                colors[1]
            ),
            Feature(
                "Lorem ipsum",
                "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium",
                "Excepteur",
                EpisodesFragment.TAG,
                colors[2]
            ),
            Feature(
                "Ipsum lorem",
                "Iste natus error sit voluptatem accusantium sed ut perspiciatis unde omnis",
                "Voluptatem",
                EpisodesFragment.TAG,
                colors[3]
            )
        )
    }

    companion object {
        const val TAG = "MainFragment"

        fun newInstance() = MainFragment()
    }
}