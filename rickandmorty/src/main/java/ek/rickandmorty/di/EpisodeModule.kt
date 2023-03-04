package ek.rickandmorty.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ek.network.Constants
import ek.network.api.RAMApiService
import ek.rickandmorty.data.QuestionsRepository
import ek.rickandmorty.data.QuestionsRepositoryImpl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class EpisodeModule {

    @Provides
    fun provideQuestionsRepository(ramApiService: RAMApiService): QuestionsRepository =
        QuestionsRepositoryImpl(ramApiService)

    @Provides
    fun provideRAMService(): RAMApiService =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.RICK_AND_MORTY_BASE_API)
            .build()
            .create(RAMApiService::class.java)
}
