package ek.questions.presentation.questions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ek.features.base.infrastructure.MviView
import ek.questions.databinding.FragmentQuestionsBinding

@AndroidEntryPoint
class QuestionsFragment : Fragment(), MviView<QuestionsState, QuestionsEvent> {

    private var _binding: FragmentQuestionsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<QuestionsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionsBinding.inflate(inflater, container, false).apply {

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.stateListener.observe(viewLifecycleOwner, ::renderViewState)
        viewModel.eventListener.observe(viewLifecycleOwner, ::handleViewEvent)
        viewModel.pushIntent(QuestionsIntent.OnViewCreated)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun renderViewState(
        state: QuestionsState
    ) = with(binding) {
        if (state.quest.isNotBlank())
            questions.text = state.quest
    }

    override fun handleViewEvent(
        event: QuestionsEvent
    ) {

    }

    companion object {
        const val TAG = "QuestionsFragment"

        fun newInstance() = QuestionsFragment()
    }
}