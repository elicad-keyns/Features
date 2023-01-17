package ek.questions.presentation.questions

import android.database.Observable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ek.core.BaseViewModel
import ek.core.LiveEvent
import ek.network.services.RAMApiService
import ek.questions.data.QuestionsRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.schedulers.RxThreadFactory
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val repository: QuestionsRepository
) : BaseViewModel<QuestionsIntent, QuestionsEvent, QuestionsState>() {

    init {
        stateListener = MutableLiveData<QuestionsState>(QuestionsState())
        eventListener = LiveEvent()
    }

    override fun onStart() {

    }

    override fun QuestionsState.reduce(intent: QuestionsIntent): QuestionsState = when(intent) {
        is QuestionsIntent.QuestReloaded -> copy(quest = intent.quest)
        is QuestionsIntent.OnViewCreated -> this
    }

    override fun processSideEffect(intent: QuestionsIntent) = when(intent) {
        is QuestionsIntent.OnViewCreated -> onViewCreated()
        else -> Unit
    }

    private fun onViewCreated() {
        val quest = repository.requestQuestions()
        pushIntent(QuestionsIntent.QuestReloaded(quest))
        getAllEpisodes()
    }

    private fun getAllEpisodes() {
        repository.requestAllEpisodes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { episodes ->
                    Log.d("EPISODES", episodes.toString())
                },
                { error ->
                    Log.d("EPISODES", error.toString())
                    error.printStackTrace()
                }
            ).disposeOnCleared()
    }

}