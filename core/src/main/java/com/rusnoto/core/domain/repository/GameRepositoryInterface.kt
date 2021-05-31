package com.rusnoto.core.domain.repository

import com.rusnoto.core.data.Resource
import com.rusnoto.core.domain.model.GameDetailModel
import com.rusnoto.core.domain.model.GameListModel
import kotlinx.coroutines.flow.Flow

interface GameRepositoryInterface {
	fun getNewRelease(): Flow<Resource<List<GameListModel>>>
	fun getGameDetail(gameId: Int): Flow<Resource<GameDetailModel>>
	fun getGameFavorite(): Flow<List<GameDetailModel>>
	fun setGameFavorite(game: GameDetailModel, status: Boolean)
}