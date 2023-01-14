package ek.features.base.infrastructure

interface MviView<State: MviViewState, Event: MviViewEvent> {

    fun renderViewState(state: State)

    fun handleViewEvent(event: Event)
}

interface MviViewState

interface MviViewEvent

interface MviViewIntent