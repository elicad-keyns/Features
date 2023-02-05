package ek.features.ui.main

import ek.core.infrastructure.MviViewEvent
import ek.core.infrastructure.MviViewIntent
import ek.core.infrastructure.MviViewState

sealed class MainIntent : MviViewIntent {
    object OnViewCreated : MainIntent()
}

sealed class MainEvent : MviViewEvent {
    object StartQuestionsScreen : MainEvent()
}

data class MainState(
    val message: String = "",
) : MviViewState {
//    class Reducer {
//        fun reduce(
//            state: MainState,
//            intent: MainIntent
//        ) = state.copy(
//            message = reduceMessage(state, intent),
//            buttonText = reduceButtonText(state, intent)
//        )
//
//        private fun reduceMessage(
//            state: MainState,
//            intent: MainIntent
//        ): String = when(intent) {
//            is MainIntent.RefreshMessage -> intent.message
//            else -> state.message
//        }
//
//        private fun reduceButtonText(
//            state: MainState,
//            intent: MainIntent
//        ): String = when(intent) {
//            is MainIntent.RefreshMessage -> intent.message
//            else -> state.buttonText
//        }
//    }
}