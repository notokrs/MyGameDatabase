package com.rusnoto.mygamedatabase.di

import com.rusnoto.core.domain.usecase.GameUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun getGameUseCase(): GameUseCase
}