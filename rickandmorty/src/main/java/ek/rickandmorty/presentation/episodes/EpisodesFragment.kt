package ek.rickandmorty.presentation.episodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ek.core.infrastructure.MviView
import ek.core.setNavigationIcon
import ek.rickandmorty.R
import ek.rickandmorty.databinding.FragmentEpisodesBinding
import ek.rickandmorty.domain.EpisodesAdapter

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
            episodes.adapter = EpisodesAdapter() { }

            toolbar.title = getString(ek.base.R.string.episodes).uppercase()
            toolbar.setNavigationIcon { activity?.onBackPressedDispatcher?.onBackPressed() }
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
    ) {

    }

    companion object {
        const val TAG = "EpisodesFragment"

        fun newInstance() = EpisodesFragment()
    }
}