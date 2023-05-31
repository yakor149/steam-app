package com.example.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SteamApiService {
    @GET("ISteamUser/GetPlayerSummaries/v2/")
    fun getPlayerSummaries(
        @Query("key") apiKey: String,
        @Query("steamids") steamId: String
    ): Call<SteamUserResponse>
}
