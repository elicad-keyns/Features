package ek.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ek.core.domain.RxTransformerFactory

@InstallIn(SingletonComponent::class)
@Module
class CoreModule {

    @Provides
    fun provideRxTransformer(): RxTransformerFactory =
        RxTransformerFactory()
}