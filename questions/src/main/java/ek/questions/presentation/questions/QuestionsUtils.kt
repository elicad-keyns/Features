package ek.questions.presentation.questions

import ek.features.base.infrastructure.MviViewEvent
import ek.features.base.infrastructure.MviViewIntent
import ek.features.base.infrastructure.MviViewState

sealed class QuestionsIntent : MviViewIntent {
    object OnViewCreated: QuestionsIntent()
    data class QuestReloaded(val quest: String): QuestionsIntent()
}
sealed class QuestionsEvent : MviViewEvent {
    object CloseScreen: QuestionsEvent()
}
data class QuestionsState(
    val error: Error? = null,
    val quest: String = ""
) : MviViewState