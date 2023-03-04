package ek.rickandmorty.presentation.episode

import android.util.Log
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ek.core.base.BaseViewModel
import ek.core.LiveEvent
import ek.core.domain.RxTransformerFactory
import ek.network.model.Episode
import ek.rickandmorty.data.QuestionsRepository
import io.reactivex.Observable
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val repository: QuestionsRepository,
    private val rxTransformerFactory: RxTransformerFactory,
) : BaseViewModel<EpisodeIntent, EpisodeEvent, EpisodeState>() {

    init {
        stateListener = MutableLiveData<EpisodeState>(EpisodeState())
        eventListener = LiveEvent()
    }

    override fun onStart() {

    }

    override fun EpisodeState.reduce(intent: EpisodeIntent): EpisodeState = when (intent) {
        is EpisodeIntent.OnViewCreated -> this
    }

    override fun processSideEffect(intent: EpisodeIntent) = when (intent) {
        is EpisodeIntent.OnViewCreated -> onViewCreated(intent.episode)
        else -> Unit
    }

    private fun onViewCreated(episode: Episode?) {
        if (episode == null) return
        getCharacters(episode)
    }

    private fun getCharacters(episode: Episode) {
        Observable.fromIterable(
            episode.characters.map {
                it.split("/").last()
            }
        )
            .concatMap { id -> repository.requestCharacter(id) }
            .compose(rxTransformerFactory.getIoToMainObservable())
            .doOnSubscribe { pushEvent(EpisodeEvent.ShowLoader(true)) }
            .doAfterTerminate { pushEvent(EpisodeEvent.ShowLoader(false)) }
            .subscribe(
                { character ->
                    Log.d("Characters", character.toString())
                    pushEvent(EpisodeEvent.AddCharacter(character))
                }, { e ->
                    Log.d("Characters", e.stackTraceToString())
                }
            ).disposeOnCleared()
    }

    private fun getAllEpisodesCoroutine(episode: Episode) {
        val characterIds = episode.characters.map {
            it.split("/").last()
        }
    }

}