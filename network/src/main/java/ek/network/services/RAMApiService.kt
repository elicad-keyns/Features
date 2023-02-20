package ek.network.services

import android.util.Log
import ek.core.model.BaseEpisode
import ek.network.model.Constants
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RAMApiService {

    @GET("episode/")
    fun getAllEpisodes(): Single<BaseEpisode>
}