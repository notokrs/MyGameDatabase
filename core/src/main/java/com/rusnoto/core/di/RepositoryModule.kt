package com.rusnoto.core.di

import com.rusnoto.core.data.GameRepository
import com.rusnoto.core.domain.repository.GameRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun getRepository(repository: GameRepository): GameRepositoryInterface
}