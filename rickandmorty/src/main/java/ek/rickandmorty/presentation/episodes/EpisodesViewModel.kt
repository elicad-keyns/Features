package ek.rickandmorty.presentation.episodes

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ek.core.BaseViewModel
import ek.core.LiveEvent
import ek.core.domain.RxTransformerFactory
import ek.rickandmorty.data.QuestionsRepository
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val repository: QuestionsRepository,
    private val rxTransformerFactory: RxTransformerFactory,
) : BaseViewModel<EpisodesIntent, EpisodesEvent, EpisodesState>() {

    init {
        stateListener = MutableLiveData<EpisodesState>(EpisodesState())
        eventListener = LiveEvent()
    }

    override fun onStart() {

    }

    override fun EpisodesState.reduce(intent: EpisodesIntent): EpisodesState = when(intent) {
        is EpisodesIntent.EpisodesLoaded -> copy(episodes = intent.episodes)
        is EpisodesIntent.OnViewCreated -> this
    }

    override fun processSideEffect(intent: EpisodesIntent) = when(intent) {
        is EpisodesIntent.OnViewCreated -> onViewCreated()
        else -> Unit
    }

    private fun onViewCreated() {
        getAllEpisodes()
    }

    private fun getAllEpisodes() {
        repository.requestAllEpisodes()
            .compose(rxTransformerFactory.getIoToMainSingle())
            .doOnSubscribe { pushEvent(EpisodesEvent.ShowLoader(true)) }
            .doAfterTerminate { pushEvent(EpisodesEvent.ShowLoader(false)) }
            .subscribe(
                { episodes -> pushIntent(EpisodesIntent.EpisodesLoaded(episodes.results)) },
                { error -> error.printStackTrace() }
            ).disposeOnCleared()
    }

}