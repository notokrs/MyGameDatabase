package com.rusnoto.core.data.source.local

import com.rusnoto.core.data.source.local.entity.GameDetailEntity
import com.rusnoto.core.data.source.local.entity.NewReleaseEntity
import com.rusnoto.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val gameDao: GameDao){
    fun getNewRelease(): Flow<List<NewReleaseEntity>> = gameDao.getNewRelease()
    suspend fun insertGameList(gameList: List<NewReleaseEntity>) = gameDao.insertGameList(gameList)

    fun getDetailGame(gameId: Int): Flow<GameDetailEntity> = gameDao.getGameDetail(gameId)
    suspend fun insertDetailGame(gameDetail: GameDetailEntity) = gameDao.insertDetailGame(gameDetail)

    fun getFavoriteGame(): Flow<List<GameDetailEntity>> =
            gameDao.getFavoriteGame()

    fun updateGame(gameDetail: GameDetailEntity, status: Boolean) {
        gameDetail.isFavorite = status
        gameDao.updateGame(gameDetail)
    }
}