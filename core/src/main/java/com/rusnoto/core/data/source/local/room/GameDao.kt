package com.rusnoto.core.data.source.local.room

import androidx.room.*
import com.rusnoto.core.data.source.local.entity.GameDetailEntity
import com.rusnoto.core.data.source.local.entity.NewReleaseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    //LIST
    @Query("SELECT * FROM newRelease_tb")
    fun getNewRelease(): Flow<List<NewReleaseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameList(gameList: List<NewReleaseEntity>)

    //DETAIL
    @Query("SELECT * FROM gameDetail_tb WHERE gameId = :gameId")
    fun getGameDetail(gameId: Int): Flow<GameDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailGame(gameDetail: GameDetailEntity)

    // FAVORITE
    @Query("SELECT * FROM gameDetail_tb WHERE isFavorite = 1")
    fun getFavoriteGame(): Flow<List<GameDetailEntity>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateGame(gameDetail: GameDetailEntity)
}