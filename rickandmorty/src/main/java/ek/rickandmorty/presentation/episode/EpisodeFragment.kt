package ek.rickandmorty.presentation.episode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ek.core.Toolbar
import ek.core.getSerializableOptimized
import ek.core.infrastructure.MviView
import ek.core.model.Episode
import ek.rickandmorty.databinding.FragmentEpisodeBinding
import ek.rickandmorty.domain.CharactersAdapter
import ek.core.model.Character

@AndroidEntryPoint
class EpisodeFragment : Fragment(), MviView<EpisodeState, EpisodeEvent> {

    private var _binding: FragmentEpisodeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<EpisodeViewModel>()

    private var episode: Episode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            episode = it.getSerializableOptimized(EPISODE_TAG, Episode::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodeBinding.inflate(inflater, container, false).apply {
            with((activity as Toolbar).getToolbar()) {
                title = episode?.name ?: getString(ek.base.R.string.episode)
            }

            episode?.let {
                episodeName.text = it.name
                episodeDate.text = it.airDate
            }
            characters.adapter = CharactersAdapter {}
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
        viewModel.pushIntent(EpisodeIntent.OnViewCreated(episode))
    }

    override fun renderViewState(
        state: EpisodeState
    ) = with(binding) {

    }

    override fun handleViewEvent(
        event: EpisodeEvent
    ) = when(event) {
        is EpisodeEvent.ShowLoader -> showLoader(event.visible)
        is EpisodeEvent.AddCharacter -> addCharacter(event.character)
        else -> Unit
    }

    private fun addCharacter(item: Character) = with(binding) {
        (characters.adapter as CharactersAdapter).setItem(item)
    }

    private fun showLoader(visible: Boolean) = with(binding) {
        progressBar.isVisible = visible
    }

    companion object {

        const val TAG = "EpisodeFragment"
        const val EPISODE_TAG = "EpisodeTag"

        fun newInstance(episode: Episode) =
            EpisodeFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(EPISODE_TAG, episode)
                }
            }
    }
}