package ek.rickandmorty.data

import ek.network.model.BaseEpisode
import ek.network.model.Character
import ek.network.api.RAMApiService
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
    private val ramApiService: RAMApiService
) : QuestionsRepository {

    override fun requestAllEpisodes(): Single<BaseEpisode> =
        ramApiService.getAllEpisodes()

    override suspend fun requestAllEpisodesCoroutines(): BaseEpisode =
        ramApiService.getAllEpisodesCoroutines()

    override fun requestCharacter(id: String): Observable<Character> =
        ramApiService.getCharacterById(id)

    override suspend fun requestCharacterCoroutine(id: String): Character =
        ramApiService.getCharacterByIdCoroutine(id)
}