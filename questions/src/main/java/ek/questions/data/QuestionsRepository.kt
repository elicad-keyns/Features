package ek.questions.data

import ek.core.model.BaseEpisode
import io.reactivex.Single

interface QuestionsRepository {
    fun requestQuestions(): String

    fun requestAllEpisodes() : Single<BaseEpisode>
}