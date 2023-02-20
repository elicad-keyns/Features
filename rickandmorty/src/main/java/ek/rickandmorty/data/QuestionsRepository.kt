package ek.rickandmorty.data

import ek.core.model.BaseEpisode
import io.reactivex.Single

interface QuestionsRepository {

    fun requestAllEpisodes() : Single<BaseEpisode>
}