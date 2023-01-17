package ek.features.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ek.core.AnimationSets
import ek.core.Navigation
import ek.core.infrastructure.MviView
import ek.features.databinding.FragmentMainBinding
import ek.questions.presentation.questions.QuestionsFragment

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
            questions.setOnClickListener { startQuestionsScreen() }
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

    private fun startQuestionsScreen() = with(activity as Navigation) {
        navigate(
            QuestionsFragment.TAG,
            QuestionsFragment.newInstance(),
            AnimationSets.CROSS_FADE
        )
    }

    companion object {
        const val TAG = "MainFragment"

        fun newInstance() = MainFragment()
    }
}