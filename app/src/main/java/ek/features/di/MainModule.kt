package ek.features.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ek.features.data.MainRepository
import ek.features.data.MainRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class MainModule {

    @Binds
    abstract fun providesMainRepository(impl: MainRepositoryImpl): MainRepository

}