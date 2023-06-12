package com.example.data.repositry

import com.example.data.RetrofitFactory
import com.example.data.SteamUserResponse

object Repository {
    private val api = RetrofitFactory.steam_Api
    suspend fun getUserInfo(apiKey: String, steamId: String): SteamUserResponse {
        return api.getPlayerSummaries(apiKey, steamId)
    }
}