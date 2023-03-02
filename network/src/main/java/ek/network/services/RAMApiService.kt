package ek.network.services

import ek.core.model.BaseEpisode
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ek.core.model.Character
import io.reactivex.Observable

interface RAMApiService {

    @GET("episode/")
    fun getAllEpisodes(): Single<BaseEpisode>

    @GET("character/{id}")
    fun getCharacterById(@Path("id") id: String): Observable<Character>
}