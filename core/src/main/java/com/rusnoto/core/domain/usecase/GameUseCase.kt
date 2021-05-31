package com.rusnoto.core.domain.usecase

import com.rusnoto.core.data.Resource
import com.rusnoto.core.domain.model.GameDetailModel
import com.rusnoto.core.domain.model.GameListModel
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getNewRelease(): Flow<Resource<List<GameListModel>>>
    fun getDetailGame(gameId: Int): Flow<Resource<GameDetailModel>>
    fun getGameFavorite(): Flow<List<GameDetailModel>>
    fun setGameFavorite(game: GameDetailModel, status: Boolean)
}