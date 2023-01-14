package ek.questions.presentation.questions

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ek.core.BaseViewModel
import ek.core.LiveEvent
import ek.questions.data.QuestionsRepository
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val repository: QuestionsRepository
) : BaseViewModel<QuestionsIntent, QuestionsEvent, QuestionsState>() {

    init {
        stateListener = MutableLiveData<QuestionsState>(QuestionsState())
        eventListener = LiveEvent()
    }

    override fun onStart() {

    }

    override fun QuestionsState.reduce(intent: QuestionsIntent): QuestionsState = when(intent) {
        is QuestionsIntent.QuestReloaded -> copy(quest = intent.quest)
        is QuestionsIntent.OnViewCreated -> this
    }

    override fun processSideEffect(intent: QuestionsIntent) = when(intent) {
        is QuestionsIntent.OnViewCreated -> onViewCreated()
        else -> Unit
    }

    private fun onViewCreated() {
        val quest = repository.requestQuestions()
        pushIntent(QuestionsIntent.QuestReloaded(quest))
    }

}