package ek.network.api

import ek.network.model.BaseEpisode
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ek.network.model.Character
import io.reactivex.Observable

interface RAMApiService {

    @GET("episode/")
    fun getAllEpisodes(): Single<BaseEpisode>

    @GET("episode/")
    suspend fun getAllEpisodesCoroutines(): BaseEpisode

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: String): Observable<Character>

    @GET("character/{id}")
    suspend fun getCharacterByIdCoroutine(@Path("id") id: String): Character
}