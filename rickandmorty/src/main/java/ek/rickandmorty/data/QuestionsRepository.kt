package ek.rickandmorty.data

import ek.core.model.BaseEpisode
import io.reactivex.Single
import ek.core.model.Character
import io.reactivex.Observable
import java.net.IDN

interface QuestionsRepository {

    fun requestAllEpisodes() : Single<BaseEpisode>

    fun requestCharacter(id: String) : Observable<Character>
}