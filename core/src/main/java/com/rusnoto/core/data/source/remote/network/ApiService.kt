package com.rusnoto.core.data.source.remote.network

import com.rusnoto.core.BuildConfig
import com.rusnoto.core.data.source.remote.response.GameDetailResponse
import com.rusnoto.core.data.source.remote.response.NewReleaseResponse
import com.rusnoto.core.utils.Helper.getDate
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    fun getNewRelease(
        @Query("dates") dates: String = getDate(),
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("page_size") pageSize: Int = 20,
    ): Call<NewReleaseResponse>

    @GET("games/{id}")
    fun getGameDetail(
            @Path("id") id: Int,
            @Query("key") key: String = BuildConfig.API_KEY
    ): Call<GameDetailResponse>
}