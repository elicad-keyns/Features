package ek.rickandmorty.data

import ek.network.model.BaseEpisode
import io.reactivex.Single
import ek.network.model.Character
import io.reactivex.Observable

interface QuestionsRepository {

    fun requestAllEpisodes() : Single<BaseEpisode>

    suspend fun requestAllEpisodesCoroutines() : BaseEpisode

    fun requestCharacter(id: String) : Observable<Character>

    suspend fun requestCharacterCoroutine(id: String) : Character
}