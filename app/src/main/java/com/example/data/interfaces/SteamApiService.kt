package com.example.data.interfaces

import com.example.data.SteamUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SteamApiService {
    @GET("ISteamUser/GetPlayerSummaries/v2/")
    suspend fun getPlayerSummaries(
        @Query("key") apiKey: String,
        @Query("steamids") steamId: String
    ): SteamUserResponse
}
