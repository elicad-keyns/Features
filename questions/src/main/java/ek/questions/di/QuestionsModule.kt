package ek.questions.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ek.questions.data.QuestionsRepository
import ek.questions.data.QuestionsRepositoryImpl


@InstallIn(SingletonComponent::class)
@Module
abstract class QuestionsModule {

    @Binds
    abstract fun provideQuestionsRepository(impl: QuestionsRepositoryImpl): QuestionsRepository
}