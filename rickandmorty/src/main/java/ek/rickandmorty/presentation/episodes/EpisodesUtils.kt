package ek.rickandmorty.presentation.episodes

import ek.core.infrastructure.MviViewEvent
import ek.core.infrastructure.MviViewIntent
import ek.core.infrastructure.MviViewState
import ek.core.model.Episode

sealed class EpisodesIntent : MviViewIntent {
    object OnViewCreated: EpisodesIntent()
    data class EpisodesLoaded(val episodes: List<Episode>): EpisodesIntent()
}
sealed class EpisodesEvent : MviViewEvent {
    object CloseScreen: EpisodesEvent()
}
data class EpisodesState(
    val error: Error? = null,
    val episodes: List<Episode> = emptyList()
) : MviViewState