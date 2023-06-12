package com.example.domain

import com.example.data.SteamUserResponse
import com.example.data.repositry.Repository

object SteamUseCase {
    suspend fun getInfo(apiKey: String, steamId: String): SteamUserResponse {
        return Repository.getUserInfo(apiKey, steamId)
    }
}