package com.rusnoto.core.data.source.remote

import android.util.Log
import com.rusnoto.core.data.source.remote.network.ApiResponse
import com.rusnoto.core.data.source.remote.network.ApiService
import com.rusnoto.core.data.source.remote.response.GameDetailResponse
import com.rusnoto.core.data.source.remote.response.NewReleaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService){
    suspend fun getNewRelease(): Flow<ApiResponse<NewReleaseResponse>> {
        return flow {
            try{
                val response = apiService.getNewRelease().await()
                val result = response.results
                if(result.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGameDetail(gameId: Int): Flow<ApiResponse<GameDetailResponse>> {
        return flow {
            try{
                val response = apiService.getGameDetail(gameId).await()
                val result = response.name
                if(result.isNotEmpty()){
                   emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}