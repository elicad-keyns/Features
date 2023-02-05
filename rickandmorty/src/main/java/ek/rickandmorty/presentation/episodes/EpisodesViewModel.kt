package ek.rickandmorty.presentation.episodes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ek.core.BaseViewModel
import ek.core.LiveEvent
import ek.rickandmorty.data.QuestionsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val repository: QuestionsRepository
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
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { episodes -> pushIntent(EpisodesIntent.EpisodesLoaded(episodes.results)) },
                { error -> error.printStackTrace() }
            ).disposeOnCleared()
    }

}