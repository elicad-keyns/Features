package ek.rickandmorty.data

import ek.core.model.BaseEpisode
import ek.network.services.RAMApiService
import io.reactivex.Single
import javax.inject.Inject

class QuestionsRepositoryImpl @Inject constructor(
) : QuestionsRepository {

    private val ramApiService = RAMApiService.create()

    override fun requestQuestions(): String = "KEK"

    override fun requestAllEpisodes(): Single<BaseEpisode> =
        ramApiService.getAllEpisodes()

}