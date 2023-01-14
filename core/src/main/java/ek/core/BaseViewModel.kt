package ek.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ek.features.base.infrastructure.MviViewEvent
import ek.features.base.infrastructure.MviViewIntent
import ek.features.base.infrastructure.MviViewState

abstract class BaseViewModel<Intent : MviViewIntent, Event : MviViewEvent, State: MviViewState> : ViewModel() {

    private var isStarted: Boolean = false

    /** Init listeners in VM
     * init {
     *     stateListener = MutableLiveData<MainState>(MainState())
     *     eventListener = LiveEvent()
     * }
     */
    lateinit var stateListener: MutableLiveData<State>
    lateinit var eventListener: LiveEvent<Event>

    private var state
        get() = stateListener.value!!
        set(value) { stateListener.value = value }

    protected abstract fun onStart()

    protected abstract fun State.reduce(intent: Intent): State

    protected abstract fun processSideEffect(intent: Intent)

    protected fun pushEvent(event: Event) {
        eventListener.value = event
    }

    fun pushIntent(intent: Intent) {
        state = state.reduce(intent)
        processSideEffect(intent)
    }

    fun start() {
        if (!isStarted) {
            isStarted = true
            onStart()
        }
    }
}