package ek.rickandmorty.presentation.episodes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ek.core.base.BaseViewModel
import ek.core.LiveEvent
import ek.core.domain.RxTransformerFactory
import ek.network.base.Resource
import ek.rickandmorty.data.QuestionsRepository
import kotlinx.coroutines.Dispatchers
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
        is EpisodesIntent.ReloadScreen -> this
        is EpisodesIntent.OnViewCreated -> this
        else -> this
    }

    override fun processSideEffect(intent: EpisodesIntent) = when(intent) {
        is EpisodesIntent.OnViewCreated -> onViewCreated()
        is EpisodesIntent.ReloadScreen -> reloadScreen()
        else -> Unit
    }

    private fun onViewCreated() {
        getAllEpisodes()
    }

    private fun reloadScreen() {
        getAllEpisodes()
    }

    private fun getAllEpisodes() {
        repository.requestAllEpisodes()
            .compose(rxTransformerFactory.getIoToMainSingle())
            .doOnSubscribe {
                pushEvent(EpisodesEvent.ShowError(false))
                pushEvent(EpisodesEvent.ShowLoader(true))
            }
            .doAfterTerminate { pushEvent(EpisodesEvent.ShowLoader(false)) }
            .subscribe(
                { episodes ->
                    pushIntent(EpisodesIntent.EpisodesLoaded(episodes.results))
                },
                { error ->
                    error.printStackTrace()
                    pushEvent(EpisodesEvent.ShowError(true))
                }
            ).disposeOnCleared()
    }

    private suspend fun getAllEpisodesCoroutine() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.requestAllEpisodesCoroutines()))
        } catch (exception: java.lang.Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred"))
        }
    }

}