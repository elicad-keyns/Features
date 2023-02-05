package ek.rickandmorty.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ek.rickandmorty.data.QuestionsRepository
import ek.rickandmorty.data.QuestionsRepositoryImpl


@InstallIn(SingletonComponent::class)
@Module
abstract class QuestionsModule {

    @Binds
    abstract fun provideQuestionsRepository(impl: QuestionsRepositoryImpl): QuestionsRepository
}