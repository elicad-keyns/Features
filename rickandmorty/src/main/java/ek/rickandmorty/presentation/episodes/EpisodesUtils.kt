package ek.rickandmorty.presentation.episodes

import androidx.annotation.LayoutRes
import ek.core.infrastructure.MviViewEvent
import ek.core.infrastructure.MviViewIntent
import ek.core.infrastructure.MviViewState
import ek.network.model.Episode

sealed class EpisodesIntent : MviViewIntent {
    object OnViewCreated: EpisodesIntent()
    object ReloadScreen: EpisodesIntent()
    object CloseScreen: EpisodesIntent()

    data class EpisodesLoaded(val episodes: List<Episode>): EpisodesIntent()
}
sealed class EpisodesEvent : MviViewEvent {
    data class ShowLoader(val visible: Boolean): EpisodesEvent()
    data class ShowError(val visible: Boolean): EpisodesEvent()
}
data class EpisodesState(
    val episodes: List<Episode> = emptyList(),

    @LayoutRes
    var errorLayoutRes: Int = -1
) : MviViewState