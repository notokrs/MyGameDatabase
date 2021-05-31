package com.rusnoto.core.domain.usecase

import com.rusnoto.core.data.GameRepository
import com.rusnoto.core.data.Resource
import com.rusnoto.core.domain.model.GameDetailModel
import com.rusnoto.core.domain.model.GameListModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameInteractor @Inject constructor(private val repository: GameRepository): GameUseCase {
    override fun getNewRelease(): Flow<Resource<List<GameListModel>>> = repository.getNewRelease()
    override fun getDetailGame(gameId: Int): Flow<Resource<GameDetailModel>> =
            repository.getGameDetail(gameId)
    override fun getGameFavorite(): Flow<List<GameDetailModel>> = repository.getGameFavorite()
    override fun setGameFavorite(game: GameDetailModel, status: Boolean) =
            repository.setGameFavorite(game, status)
}