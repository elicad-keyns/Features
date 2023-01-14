package ek.features.ui.main

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ek.core.BaseViewModel
import ek.core.LiveEvent
import ek.features.data.MainRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : BaseViewModel<MainIntent, MainEvent, MainState>() {

    init {
        stateListener = MutableLiveData<MainState>(MainState())
        eventListener = LiveEvent()
    }

    override fun onStart() {

    }

    override fun MainState.reduce(intent: MainIntent): MainState = when (intent) {
        is MainIntent.OnViewCreated -> this
    }

    override fun processSideEffect(intent: MainIntent) = when (intent) {
        is MainIntent.OnViewCreated -> onViewCreated()
        else -> Unit
    }

    private fun onViewCreated() {

    }
}