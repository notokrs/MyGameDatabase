package com.rusnoto.core.data

import com.rusnoto.core.data.source.local.LocalDataSource
import com.rusnoto.core.data.source.remote.RemoteDataSource
import com.rusnoto.core.data.source.remote.network.ApiResponse
import com.rusnoto.core.data.source.remote.response.GameDetailResponse
import com.rusnoto.core.data.source.remote.response.NewReleaseResponse
import com.rusnoto.core.domain.model.GameDetailModel
import com.rusnoto.core.domain.model.GameListModel
import com.rusnoto.core.domain.repository.GameRepositoryInterface
import com.rusnoto.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
): GameRepositoryInterface {
    override fun getNewRelease(): Flow<Resource<List<GameListModel>>> =
        object : NetworkBoundResponse<List<GameListModel>, NewReleaseResponse>(){
            override fun loadFromDB(): Flow<List<GameListModel>> {
                return localDataSource.getNewRelease().map {
                    DataMapper.mapListEntitiesToListDomain(it)
                }
            }

            override fun shouldFetch(data: List<GameListModel>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<NewReleaseResponse>> {
                return remoteDataSource.getNewRelease()
            }

            override suspend fun saveCallResult(data: NewReleaseResponse) {
                val gameList = DataMapper.mapListResponsesToListEntities(data)
                localDataSource.insertGameList(gameList)
            }
        }.asFlow()

    override fun getGameDetail(gameId: Int): Flow<Resource<GameDetailModel>> =
            object : NetworkBoundResponse<GameDetailModel, GameDetailResponse>(){
                override fun loadFromDB(): Flow<GameDetailModel> {
                    return localDataSource.getDetailGame(gameId).map {
                        DataMapper.mapDetailEntitiesToDetailDomain(it)
                    }
                }

                override fun shouldFetch(data: GameDetailModel?): Boolean {
                    return data?.name == null
                }

                override suspend fun createCall(): Flow<ApiResponse<GameDetailResponse>> {
                    return remoteDataSource.getGameDetail(gameId)
                }

                override suspend fun saveCallResult(data: GameDetailResponse) {
                    val gameDetail = DataMapper.mapDetailResponseToDetailEntity(data)
                    localDataSource.insertDetailGame(gameDetail)
                }
            }.asFlow()

    override fun getGameFavorite(): Flow<List<GameDetailModel>> {
        return localDataSource.getFavoriteGame().map {
            DataMapper.mapDetailEntityToListModel(it)
        }
    }

    override fun setGameFavorite(game: GameDetailModel, status: Boolean) {
        val gameDetail = DataMapper.mapDetailDomainToDetailEntity(game)
        CoroutineScope(IO).launch {
            localDataSource.updateGame(gameDetail, status)
        }
    }
}