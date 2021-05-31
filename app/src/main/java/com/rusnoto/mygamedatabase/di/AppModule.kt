package com.rusnoto.mygamedatabase.di

import com.rusnoto.core.domain.usecase.GameInteractor
import com.rusnoto.core.domain.usecase.GameUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun getGameUseCase(gameInteractor: GameInteractor): GameUseCase
}