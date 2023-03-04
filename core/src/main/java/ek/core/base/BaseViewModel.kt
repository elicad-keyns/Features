package ek.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ek.core.LiveEvent
import ek.core.infrastructure.MviViewEvent
import ek.core.infrastructure.MviViewIntent
import ek.core.infrastructure.MviViewState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel<Intent : MviViewIntent, Event : MviViewEvent, State: MviViewState> : ViewModel() {

    protected val compositeDisposable by lazy(::CompositeDisposable)

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

    protected fun Disposable.disposeOnCleared(): Disposable {
        compositeDisposable.add(this)
        return this
    }

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