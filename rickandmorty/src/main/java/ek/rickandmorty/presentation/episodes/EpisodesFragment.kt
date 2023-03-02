package ek.rickandmorty.presentation.episodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ek.core.AnimationSets
import ek.core.Navigation
import ek.core.Toolbar
import ek.core.infrastructure.MviView
import ek.core.model.Episode
import ek.core.setNavigationIcon
import ek.rickandmorty.databinding.FragmentEpisodesBinding
import ek.rickandmorty.domain.EpisodesAdapter
import ek.rickandmorty.presentation.episode.EpisodeFragment

@AndroidEntryPoint
class EpisodesFragment : Fragment(), MviView<EpisodesState, EpisodesEvent> {

    private var _binding: FragmentEpisodesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<EpisodesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodesBinding.inflate(inflater, container, false).apply {
            episodes.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            episodes.adapter = EpisodesAdapter { startEpisodeScreen(it) }

            with((activity as Toolbar).getToolbar()) {
                title = getString(ek.base.R.string.episodes).uppercase()
                setNavigationIcon { activity?.onBackPressedDispatcher?.onBackPressed() }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.stateListener.observe(viewLifecycleOwner, ::renderViewState)
        viewModel.eventListener.observe(viewLifecycleOwner, ::handleViewEvent)
        viewModel.pushIntent(EpisodesIntent.OnViewCreated)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun renderViewState(
        state: EpisodesState
    ) = with(binding) {
        (episodes.adapter as EpisodesAdapter).setItems(state.episodes)
    }

    override fun handleViewEvent(
        event: EpisodesEvent
    ) = when(event) {
        is EpisodesEvent.ShowLoader -> showLoader(event.visible)
        else -> Unit
    }

    private fun showLoader(visible: Boolean) = with(binding) {
        progressBar.isVisible = visible
    }

    private fun startEpisodeScreen(episode: Episode) {
        (activity as Navigation).navigate(
            EpisodeFragment.TAG,
            EpisodeFragment.newInstance(episode),
            AnimationSets.CROSS_FADE
        )
    }

    companion object {
        const val TAG = "EpisodesFragment"

        fun newInstance() = EpisodesFragment()
    }
}