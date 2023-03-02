package ek.rickandmorty.presentation.episode

import ek.core.infrastructure.MviViewEvent
import ek.core.infrastructure.MviViewIntent
import ek.core.infrastructure.MviViewState
import ek.core.model.Episode
import ek.core.model.Character

sealed class EpisodeIntent : MviViewIntent {
    data class OnViewCreated(val episode: Episode?): EpisodeIntent()
}

sealed class EpisodeEvent : MviViewEvent {
    object CloseScreen: EpisodeEvent()

    data class AddCharacter(val character: Character): EpisodeEvent()
    data class ShowLoader(val visible: Boolean): EpisodeEvent()
}
data class EpisodeState(
    val error: Error? = null
) : MviViewState