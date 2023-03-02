package ek.rickandmorty.data

import ek.core.model.BaseEpisode
import ek.core.model.Character
import ek.network.services.RAMApiService
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
    private val ramApiService: RAMApiService
) : QuestionsRepository {

    override fun requestAllEpisodes(): Single<BaseEpisode> =
        ramApiService.getAllEpisodes()

    override fun requestCharacter(id: String): Observable<Character> =
        ramApiService.getCharacterById(id)
}